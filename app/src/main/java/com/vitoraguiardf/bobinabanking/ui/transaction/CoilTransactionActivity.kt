package com.vitoraguiardf.bobinabanking.ui.transaction

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.vitoraguiardf.bobinabanking.R
import com.vitoraguiardf.bobinabanking.databinding.ActivityCoilTransactionBinding
import com.vitoraguiardf.bobinabanking.ui.transaction.fragments.RecipientFragment
import com.vitoraguiardf.bobinabanking.ui.transaction.fragments.SenderFragment
import com.vitoraguiardf.bobinabanking.utils.activities.CustomActivity

class CoilTransactionActivity : CustomActivity<ActivityCoilTransactionBinding>() {

    private val sharedModel: SharedViewModel by viewModels()

    override fun viewBindingInflate(): ActivityCoilTransactionBinding {
        return ActivityCoilTransactionBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            sharedModel.transferenceForm.scenario.observe(this, Observer {
                val scenario = it?: return@Observer
                when (scenario) {
                    /** Registro de Uso (Impressão)
                     *  - TYPE: Tipo de Transferência: Utilização :: AUTOMATIC
                     *  - DESTIN: NULL :: AUTOMATIC
                     *  - ORIGIN: Selecionar a conta de origem e quantidade (SenderFragment) :: BY USER
                     *  - Confirmação da Transação (ConfirmationFragment) :: BY USER
                     */
                    TransferenceScenarios.USAGE -> {
                        sharedModel.transferenceForm.setType("USAGE")
                        sharedModel.transferenceForm.setRecipient(null)
                        fragmentTransaction.replace(R.id.container, SenderFragment.newInstance())
                    }
                    /** Transferência entre contas
                     *  - TYPE: Tipo de Transferência: Transferência :: AUTOMATIC
                     *  - DESTIN: Selecionar a conta do destinatário (RecipientFragment) :: BY USER
                     *  - ORIGIN: Selecionar a conta de origem e quantidade (SenderFragment) :: BY USER
                     *  - Confirmação da Transação (ConfirmationFragment) :: BY USER
                     */
                    TransferenceScenarios.TRANSFERENCE -> {
                        sharedModel.transferenceForm.setType("TRANSFERENCE")
                        fragmentTransaction.replace(R.id.container, RecipientFragment.newInstance())
                    }
                    else -> {
                        fragmentTransaction.replace(R.id.container, RecipientFragment.newInstance())
                    }
                }
                fragmentTransaction.commitNow()
            })
        }
        intent.serializable<TransferenceScenarios>("SCENARIO")?.let {
            sharedModel.transferenceForm.setScenario(it)
        }
    }

}