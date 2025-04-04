package com.hanatransaction.ui.transactions

import android.os.Bundle
import androidx.navigation.NavDirections
import com.hanatransaction.R
import kotlin.Int
import kotlin.Long

public class TransactionsFragmentDirections private constructor() {
  private data class ActionTransactionsFragmentToTransactionDetailFragment(
    public val transactionId: Long = -1L,
  ) : NavDirections {
    public override val actionId: Int =
        R.id.action_transactionsFragment_to_transactionDetailFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putLong("transactionId", this.transactionId)
        return result
      }
  }

  public companion object {
    public fun actionTransactionsFragmentToTransactionDetailFragment(transactionId: Long = -1L):
        NavDirections = ActionTransactionsFragmentToTransactionDetailFragment(transactionId)
  }
}
