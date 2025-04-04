package com.hanatransaction.ui.settings

import android.os.Bundle
import androidx.navigation.NavDirections
import com.hanatransaction.R
import kotlin.Int

public class SettingsFragmentDirections private constructor() {
  private data class ActionSettingsFragmentToPinFragment(
    public val mode: Int = 2,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_settingsFragment_to_pinFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putInt("mode", this.mode)
        return result
      }
  }

  public companion object {
    public fun actionSettingsFragmentToPinFragment(mode: Int = 2): NavDirections =
        ActionSettingsFragmentToPinFragment(mode)
  }
}
