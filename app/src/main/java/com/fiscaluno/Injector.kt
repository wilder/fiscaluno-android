package com.fiscaluno

import android.content.Context
import com.fiscaluno.helper.PreferencesManager
import com.fiscaluno.network.AuthInterceptor
import com.fiscaluno.network.FiscalunoApi
import com.fiscaluno.network.ServiceHolder
import com.fiscaluno.repository.UserRepository
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

class Injector(val context: Context) {

    val dependency = Kodein {

        bind<FiscalunoApi>() with singleton {
            ServiceHolder(instance(), AuthInterceptor(instance()))
                    .create(FiscalunoApi::class.java)
        }

        bind<String>() with singleton {
            "https://fiscaluno.herokuapp.com/"
        }

        bind<UserRepository>() with singleton {
            object : UserRepository {
                override fun getUserToken(): String? =
                        PreferencesManager(this@Injector.context).token

                override fun saveUserToken(token: String) {
                    PreferencesManager(this@Injector.context).token = token
                }
            }

        }
    }


}