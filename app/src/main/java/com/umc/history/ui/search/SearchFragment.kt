package com.umc.history.ui.search

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.history.OneStory
import com.umc.history.R
import com.umc.history.databinding.FragmentSearchBinding
import com.umc.history.util.Util
import retrofit2.http.Body

class SearchFragment : Fragment(), Util {
    lateinit var binding : FragmentSearchBinding
    private var storyDatas = ArrayList<Body>()
    var flag = 0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)


        binding.searchChangeIv.setOnClickListener {


        }

        binding.searchSearchEt.setOnEditorActionListener(object : TextView.OnEditorActionListener{
            override fun onEditorAction(p0: TextView?, p1: Int, p2: KeyEvent?): Boolean {
                if (p1 == EditorInfo.IME_ACTION_SEARCH){
                    hideKeyboard(binding.searchSearchEt, requireContext())
                    return true
                }
                return false
            }
        })

        val dividerItemDecoration = DividerItemDecoration(binding.searchStoryRv.context, LinearLayoutManager(activity).orientation)
        binding.searchStoryRv.addItemDecoration(dividerItemDecoration)
        return binding.root
    }
    private fun showSearchOption(){
        val builder = AlertDialog.Builder(activity)
        val dialogView = layoutInflater.inflate(R.layout.dialog_search, null)
        builder.setView(dialogView)
        val alertDialog = builder.create()
        val window = alertDialog.window
        window?.setGravity(Gravity.BOTTOM)
        builder.setView(dialogView)
        alertDialog.show()
        alertDialog.findViewById<TextView>(R.id.dialog_title_tv).setOnClickListener {
            flag = 1
            alertDialog.hide()
        }
        alertDialog.findViewById<TextView>(R.id.dialog_content_tv).setOnClickListener {
            flag = 0
            alertDialog.hide()
        }
    }

//    private fun hideKeyboard(editText: EditText){
//        (requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).apply {
//            hideSoftInputFromWindow(editText.windowToken, 0)
//        }
//    }

}