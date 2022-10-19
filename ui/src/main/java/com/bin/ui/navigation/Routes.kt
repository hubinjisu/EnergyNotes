package com.bin.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.bin.presentation.viewmodel.AboutViewModel
import com.bin.presentation.viewmodel.NotesViewModel
import com.bin.presentation.viewmodel.RecordViewModel
import com.bin.ui.ScreenAbout
import com.bin.ui.ScreenNotes
import com.bin.ui.ScreenRecord

object NotesRoute : NavRoute<NotesViewModel> {
    override val route = "notes/"

    @Composable
    override fun viewModel(): NotesViewModel = hiltViewModel()

    @Composable
    override fun Content(viewModel: NotesViewModel) = ScreenNotes(
        requireNotNull(viewModel.energyNotes.collectAsState().value),
        onRecordClicked = { viewModel.navigateToRoute(RecordRoute.route) },
        onAboutClicked = { viewModel.navigateToRoute(AboutRoute.route) }
    )
}

object RecordRoute : NavRoute<RecordViewModel> {
    override val route = "record/"

    @Composable
    override fun viewModel(): RecordViewModel = hiltViewModel()

    @Composable
    override fun Content(viewModel: RecordViewModel) = ScreenRecord(
        onNotesClicked = { viewModel.navigateToRoute(NotesRoute.route) },
        onAboutClicked = { viewModel.navigateToRoute(AboutRoute.route) },
        onRecordClicked = { waterReading, electricityReading, gasReading ->
            viewModel.recordWaterNote(waterReading.toLong())
            viewModel.recordElectricityNote(electricityReading.toLong())
            viewModel.recordGasNote(gasReading.toLong())
        }
    )
}

object AboutRoute : NavRoute<AboutViewModel> {
    override val route = "about/"

    @Composable
    override fun viewModel(): AboutViewModel = hiltViewModel()

    @Composable
    override fun Content(viewModel: AboutViewModel) = ScreenAbout(
        onRecordClicked = { viewModel.navigateToRoute(RecordRoute.route) },
        onNotesClicked = { viewModel.navigateToRoute(NotesRoute.route) }
    )
}