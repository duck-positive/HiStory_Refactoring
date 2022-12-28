package com.umc.history

interface DeleteView {
    fun onDeleteLoading()
    fun onDeleteSuccess(response : Boolean)
    fun onDeleteFailure()
}