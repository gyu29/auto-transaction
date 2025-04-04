package com.hanatransaction.ui.transactions

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.Long
import kotlin.jvm.JvmStatic

public data class TransactionDetailFragmentArgs(
  public val transactionId: Long = -1L,
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putLong("transactionId", this.transactionId)
    return result
  }

  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    result.set("transactionId", this.transactionId)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): TransactionDetailFragmentArgs {
      bundle.setClassLoader(TransactionDetailFragmentArgs::class.java.classLoader)
      val __transactionId : Long
      if (bundle.containsKey("transactionId")) {
        __transactionId = bundle.getLong("transactionId")
      } else {
        __transactionId = -1L
      }
      return TransactionDetailFragmentArgs(__transactionId)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle):
        TransactionDetailFragmentArgs {
      val __transactionId : Long?
      if (savedStateHandle.contains("transactionId")) {
        __transactionId = savedStateHandle["transactionId"]
        if (__transactionId == null) {
          throw IllegalArgumentException("Argument \"transactionId\" of type long does not support null values")
        }
      } else {
        __transactionId = -1L
      }
      return TransactionDetailFragmentArgs(__transactionId)
    }
  }
}
