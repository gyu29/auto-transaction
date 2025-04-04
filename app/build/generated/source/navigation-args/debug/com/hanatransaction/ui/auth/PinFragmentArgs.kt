package com.hanatransaction.ui.auth

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.Int
import kotlin.jvm.JvmStatic

public data class PinFragmentArgs(
  public val mode: Int = 1,
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putInt("mode", this.mode)
    return result
  }

  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    result.set("mode", this.mode)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): PinFragmentArgs {
      bundle.setClassLoader(PinFragmentArgs::class.java.classLoader)
      val __mode : Int
      if (bundle.containsKey("mode")) {
        __mode = bundle.getInt("mode")
      } else {
        __mode = 1
      }
      return PinFragmentArgs(__mode)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle): PinFragmentArgs {
      val __mode : Int?
      if (savedStateHandle.contains("mode")) {
        __mode = savedStateHandle["mode"]
        if (__mode == null) {
          throw IllegalArgumentException("Argument \"mode\" of type integer does not support null values")
        }
      } else {
        __mode = 1
      }
      return PinFragmentArgs(__mode)
    }
  }
}
