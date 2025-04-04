package com.hanatransaction.ui.transactions

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.hanatransaction.R

public class NewTransactionFragmentDirections private constructor() {
  public companion object {
    public fun actionNewTransactionFragmentToHomeFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_newTransactionFragment_to_homeFragment)
  }
}
