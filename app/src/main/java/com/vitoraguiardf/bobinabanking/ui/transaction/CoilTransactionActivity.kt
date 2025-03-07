package com.vitoraguiardf.bobinabanking.ui.transaction

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.vitoraguiardf.bobinabanking.R
import com.vitoraguiardf.bobinabanking.databinding.ActivityCoilTransactionBinding
import com.vitoraguiardf.bobinabanking.ui.ViewModelFactory
import com.vitoraguiardf.bobinabanking.ui.transaction.fragments.ConfirmationFragment
import com.vitoraguiardf.bobinabanking.ui.transaction.fragments.DetailsFragment
import com.vitoraguiardf.bobinabanking.ui.transaction.fragments.QrCodeScanFragment
import com.vitoraguiardf.bobinabanking.ui.transaction.fragments.RecipientFragment
import com.vitoraguiardf.bobinabanking.utils.FastMessages
import com.vitoraguiardf.bobinabanking.utils.viewbinding.AbstractAppCompatActivity

class CoilTransactionActivity : AbstractAppCompatActivity<ActivityCoilTransactionBinding>() {

    private lateinit var sharedModel: SharedViewModel

    override fun viewBindingInflate(): ActivityCoilTransactionBinding {
        return ActivityCoilTransactionBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModelProvider = ViewModelProvider(this, ViewModelFactory(resources))
        sharedModel = viewModelProvider[SharedViewModel::class]

        if (savedInstanceState == null) {
            sharedModel.transferenceForm.scenario.observe(this, Observer {
                val scenario = it?: return@Observer
                val fragmentTransaction = supportFragmentManager.beginTransaction()
                when (scenario) {
                    /** Registro de Uso (Impressão)
                     *  - DESTIN: NULL :: AUTOMATIC
                     *  - ORIGIN: Selecionar a conta de origem e quantidade (SenderFragment) :: BY USER
                     *  - Confirmação da Transação (ConfirmationFragment) :: BY USER
                     */
                    TransferenceScenarios.USAGE -> {
                        sharedModel.transferenceForm.setType(3)
                        fragmentTransaction.replace(R.id.container, DetailsFragment.newInstance())
                    }
                    /** Transferência entre contas
                     *  - DESTIN: Selecionar a conta do destinatário (RecipientFragment) :: BY USER
                     *  - ORIGIN: Selecionar a conta de origem e quantidade (SenderFragment) :: BY USER
                     *  - Confirmação da Transação (ConfirmationFragment) :: BY USER
                     */
                    TransferenceScenarios.TRANSFERENCE -> {
                        sharedModel.transferenceForm.setType(2)
                        fragmentTransaction.replace(R.id.container, RecipientFragment.newInstance())
                    }
                    /** Transferência entre contas - QrCode
                     *  - DESTIN: Selecionar a conta do destinatário (QrCodeFragment) :: SEMI AUTOMATIC
                     *  - ORIGIN: Selecionar a conta de origem e quantidade (SenderFragment) :: BY USER
                     *  - Confirmação da Transação (ConfirmationFragment) :: BY USER
                     */
                    TransferenceScenarios.TRANSFERENCE_QRCODE -> {
                        sharedModel.transferenceForm.setType(2)
                        fragmentTransaction.replace(R.id.container, QrCodeScanFragment.newInstance())
                    }
                }.commitNow()
            })
            sharedModel.transferenceForm.recipient.observe(this, Observer {
                it?:return@Observer
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, DetailsFragment.newInstance())
                    .commitNow()
            })
            sharedModel.transferenceForm.quantity.observe(this, Observer {
                it?:return@Observer
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, ConfirmationFragment.newInstance())
                    .commitNow()
            })
            sharedModel.transferenceForm.confirmation.observe(this, Observer {
                if (!it) return@Observer
                sharedModel.postTransaction()
            })
            sharedModel.form.result.observe(this, Observer {
                val message = it?:return@Observer
                FastMessages.success(this@CoilTransactionActivity, message.message)
                setResult(RESULT_OK)
                finish()
            })
            sharedModel.form.throwable.observe(this, Observer {
                val error = it?:return@Observer
                FastMessages.error(this@CoilTransactionActivity, error.message)
            })
        }
        intent.serializable<TransferenceScenarios>("SCENARIO")?.let {
            sharedModel.transferenceForm.setScenario(it)
        }
    }

}