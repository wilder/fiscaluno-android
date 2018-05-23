package com.fiscaluno

import android.content.Context
import com.fiscaluno.data.FiscalunoApi
import com.fiscaluno.data.ServiceHolder
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

class Injector(val context: Context) {

    val dependency = Kodein {

        bind<FiscalunoApi>() with singleton {
            ServiceHolder(instance())
                    .create(FiscalunoApi::class.java)
        }
        bind<String>() with singleton {
            "https://fiscaluno.herokuapp.com/"
        }
    }


}