package com.umc.history

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.history.databinding.FragmentWriteBinding
import com.umc.history.ui.MainActivity
import com.umc.history.ui.home.HomeFragment
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


class WriteFragment : Fragment() {
    private val requiredPermission = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
    val PERMISSION_REQUEST_CODE = 10000
    lateinit var binding : FragmentWriteBinding
    private var hashtagList = arrayListOf<String>()
    private var imageList = arrayListOf<Image>()
    private val REQUEST_GET_IMAGE = 105
    private var uriList = arrayListOf<Uri>()
    private var pathList = arrayListOf<MultipartBody.Part>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWriteBinding.inflate(inflater, container, false)
        binding.writeHashtagRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.writeHashtagRv.adapter = HashtagRVAdapter(hashtagList)
        var category = ""
        binding.writeCategoryRb.setOnCheckedChangeListener { _, id ->
            when (id){
                R.id.write_category_korean_rb -> category = "KOREAN"
                R.id.write_category_western_rb -> category = "WESTERN"
                R.id.write_category_oriental_rb -> category = "ASIAN"
                R.id.write_category_etc_rb -> category = "ETC"
            }
            hideWarning(binding.writeCategoryWarningIv,binding.writeCategoryWarningTv)
        }

        binding.writeConfirmBtn.setOnClickListener {
            val spf = context?.getSharedPreferences("token", AppCompatActivity.MODE_PRIVATE)
            val accessToken = spf?.getString("accessToken", null)
            when{
                binding.writeTitleEt.text.isEmpty() -> showWarning(binding.writeTitleWarningIv, binding.writeTitleWarningTv)
                category == "" -> showWarning(binding.writeCategoryWarningIv, binding.writeCategoryWarningTv)
                else -> {
                    val storyService = StoryService()
                    val id = spf?.getString("id",null)
                    if(uriList.isNotEmpty()) {
                        for(path in uriList) {
                            val file = File(absolutePath(path))
                            val requestFile = RequestBody.create(MediaType.parse("image/*"), file)
                            val body =
                                MultipartBody.Part.createFormData("imageList", file.name, requestFile)
                            pathList.add(body)
                        }
                        Log.d("image_test", "$pathList")
                        //storyService.writeStory(accessToken,pathList, id!!, binding.writeTitleEt.text.toString(), category, binding.writeStoryEt.text.toString(), hashtagList)
                    } else {
                        //storyService.writeStory(accessToken, pathList, id!!, binding.writeTitleEt.text.toString(), category, binding.writeStoryEt.text.toString(), hashtagList)
                    }
                    val spf = requireContext().getSharedPreferences("story", AppCompatActivity.MODE_PRIVATE)
                    val token = spf.edit()
                    token.putString("title",binding.writeTitleEt.text.toString())
                    token.putString("story",binding.writeStoryEt.text.toString())
                    token.commit()

                    (context as MainActivity).supportFragmentManager.beginTransaction()
                        .replace(R.id.fl_container, HomeFragment())
                        .commit()
                }

            }
        }

        binding.writeImgRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.writeImgRv.adapter = ImageRVAdapter(imageList)

        binding.writeTitleEt.onFocusChangeListener = View.OnFocusChangeListener{ _, p1 ->
            if(p1){
            } else {
                hideWarning(binding.writeTitleWarningIv,binding.writeTitleWarningTv)
                hideKeyboard(binding.writeTitleEt)
            }
        }

        binding.writeHashtagEt.onFocusChangeListener = View.OnFocusChangeListener{ _, p1 ->
            if(p1){
            } else {
                if(hashtagList.size == 10){

                } else if(binding.writeHashtagEt.text.isNotEmpty()){
                    addHashTag()
                }
            }
        }
        binding.writeHashtagEt.setOnEditorActionListener(object : TextView.OnEditorActionListener{
            override fun onEditorAction(p0: TextView?, p1: Int, p2: KeyEvent?): Boolean {
                if(hashtagList.size == 10){

                }else if (p1 == EditorInfo.IME_ACTION_DONE){
                    hideKeyboard(binding.writeHashtagEt)
                    addHashTag()
                    return true
                }
                return false
            }
        })

        binding.writeImgLo.setOnClickListener {
            checkPermission()
        }

        return binding.root
    }


    private fun addHashTag(){
        val text = binding.writeHashtagEt.text.toString().trim()
        hashtagList.add("#${text}")
        binding.writeHashtagEt.setText("")
        binding.writeHashtagRv.adapter?.notifyDataSetChanged()
    }

    private fun addImage(){
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_GET_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            val uri = data?.data
            if(requestCode == REQUEST_GET_IMAGE){
                val bitmap = MediaStore.Images.Media.getBitmap(requireContext().contentResolver, uri)
                imageList.add(Image(bitmap))
                uriList.add(uri!!)
                binding.writeImgRv.adapter?.notifyItemInserted(imageList.lastIndex)
            }
        }
    }


    private fun hideKeyboard(editText: EditText){
        (requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).apply {
            hideSoftInputFromWindow(editText.windowToken, 0)
        }
    }

    private fun showWarning(iv : ImageView, tv: TextView){
        iv.visibility = View.VISIBLE
        tv.visibility = View.VISIBLE
    }
    private fun hideWarning(iv : ImageView, tv: TextView){
        iv.visibility = View.GONE
        tv.visibility = View.GONE
    }

    fun absolutePath(path: Uri) : String? {
        val context = requireContext()
        val contentResolver = context.contentResolver ?: return null

        val filePath = (context.applicationInfo.dataDir + File.separator + System.currentTimeMillis())
        val file = File(filePath)
        try{
            val inputStream = contentResolver.openInputStream(path) ?: return null
            val outputStream = FileOutputStream(file)
            val buf = ByteArray(1024)
            var len : Int
            while(inputStream.read(buf).also {len = it} > 0) outputStream.write(buf, 0, len)
            outputStream.close()
            inputStream.close()
        } catch (e : IOException){
            e.printStackTrace()
        }
        return file.absolutePath
    }

   private fun checkPermission(){
       when {
           ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                   == PERMISSION_GRANTED -> {
                       if(imageList.size == 5){}
                       else addImage()
           }
           else -> {
                requestPermissions(requireActivity(), arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), PERMISSION_REQUEST_CODE)

           }
       }
   }

}