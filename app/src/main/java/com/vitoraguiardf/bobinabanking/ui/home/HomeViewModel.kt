package com.vitoraguiardf.bobinabanking.ui.home

import com.vitoraguiardf.bobinabanking.data.entity.User
import com.vitoraguiardf.bobinabanking.data.rest.AuthRepository
import com.vitoraguiardf.bobinabanking.utils.viewmodel.ViewModel

class HomeViewModel: ViewModel<Int, User, Void>() {

    companion object {
        internal val repository: AuthRepository = AuthRepository()
    }

}