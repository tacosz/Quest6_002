package com.example.myarsitekturmvvm.view.uicontroller

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myarsitekturmvvm.model.DataJK.JenisK
import com.example.myarsitekturmvvm.view.FormIsian
import com.example.myarsitekturmvvm.view.TampilData
import com.example.myarsitekturmvvm.viewmodel.SiswaViewModel

enum class Navigasi {
    Formulirku,
    Detail
}

@Composable
fun DataApp(
    modifier: Modifier,
    viewModel: SiswaViewModel = viewModel(),
    navController : NavHostController = rememberNavController()
){
    Scaffold { isiRuang->
        val uiState = viewModel.statusUI.collectAsState()
        NavHost(
            navController = navController,
            startDestination = Navigasi.Formulirku.name,

            modifier = Modifier.padding(paddingValues = isiRuang)){
            composable(route = Navigasi.Formulirku.name){
                val konteks = LocalContext.current
                FormIsian (
                    pilihanJK = JenisK.map { id -> konteks.resources.getString(id) },
                    onSubmitBttnClicked = {
                        navController.navigate(route = Navigasi.Detail.name)
                    }
                )
            }
            composable(route = Navigasi.Detail.name){
                TampilData (
                    onBackBtnClick = {
                        cancelAndBackToFormulir(navController)
                    }
                )
            }
        }
    }
}

private fun cancelAndBackToFormulir(
    navController: NavController
){
    navController.popBackStack(route = Navigasi.Formulirku.name, inclusive = false)
}