package br.duccipopi.met.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import br.duccipopi.met.model.IRepository

class ArtworkViewModel(val app: Application, val repository: IRepository) : AndroidViewModel(app) {

}