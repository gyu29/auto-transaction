package com.hanatransaction.ui.home

import android.os.Bundle
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.hanatransaction.R
import kotlin.Int
import kotlin.Long

public class HomeFragmentDirections private constructor() {
  private data class ActionHomeFragmentToTransactionDetailFragment(
    public val transactionId: Long = -1L,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_homeFragment_to_transactionDetailFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putLong("transactionId", this.transactionId)
        return result
      }
  }

  public companion object {
    public fun actionHomeFragmentToNewTransactionFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_homeFragment_to_newTransactionFragment)

    public fun actionHomeFragmentToTransactionDetailFragment(transactionId: Long = -1L):
        NavDirections = ActionHomeFragmentToTransactionDetailFragment(transactionId)
  }
}
