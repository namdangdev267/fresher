package com.misa.fresher.data

/**
 * - interface's purpose:
 *
 * @author HTLong
 * @edit_at 2022-03-21
 */

interface IOnLoadedCallback<D, E> {
    fun onSuccess(data: D)
    fun onFailure(error: E)
    fun onException(e: Exception)
}