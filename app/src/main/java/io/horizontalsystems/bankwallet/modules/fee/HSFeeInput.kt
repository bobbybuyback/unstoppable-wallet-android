package io.horizontalsystems.bankwallet.modules.fee

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import io.horizontalsystems.bankwallet.R
import io.horizontalsystems.bankwallet.entities.CurrencyValue
import io.horizontalsystems.bankwallet.modules.evmfee.EvmFeeCell
import io.horizontalsystems.bankwallet.modules.send.SendModule
import java.math.BigDecimal

@Composable
fun HSFeeInput(
    coinCode: String,
    coinDecimal: Int,
    fiatDecimal: Int,
    fee: BigDecimal?,
    amountInputType: SendModule.InputType,
    rate: CurrencyValue?,
    onClick: (() -> Unit)? = null,
) {
    val viewModel = viewModel<FeeInputViewModel>(
        factory = FeeInputModule.Factory(
            coinCode,
            coinDecimal,
            fiatDecimal
        )
    )
    val formatted = viewModel.formatted

    LaunchedEffect(fee, amountInputType, rate) {
        viewModel.fee = fee
        viewModel.amountInputType = amountInputType
        viewModel.rate = rate
        viewModel.refreshFormatted()
    }

    EvmFeeCell(
        title = stringResource(R.string.Send_Fee),
        value = formatted,
        loading = false,
        viewState = null,
        onClick = onClick
    )
}