package br.duccipopi.met

import android.app.Application
import br.duccipopi.met.model.IRepository
import br.duccipopi.met.model.Repository
import br.duccipopi.met.model.data.MetMuseumDao
import br.duccipopi.met.model.data.MetMuseumDatabase
import br.duccipopi.met.model.remote.MetMuseumApi
import br.duccipopi.met.model.remote.metMuseumService
import br.duccipopi.met.viewmodel.DepartmentViewModel
import br.duccipopi.met.viewmodel.GalleryViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        val module = module {

            viewModel {
                DepartmentViewModel(get(), get())
            }
            viewModel {
                GalleryViewModel(get(), get())
            }
            single { metMuseumService as MetMuseumApi }
            single { MetMuseumDatabase.getInstance(this@MyApp).metMuseumDao as MetMuseumDao }
            single { Repository(get(), get()) as IRepository }
        }

        startKoin {
            androidContext(this@MyApp)
            modules(listOf(module))
        }

    }


}