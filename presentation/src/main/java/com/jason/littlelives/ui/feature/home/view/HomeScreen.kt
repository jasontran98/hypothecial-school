package com.jason.littlelives.ui.feature.home.view

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.jason.littlelives.ui.component.BottomNavItem
import com.jason.littlelives.ui.component.CustomBottomNavigation
import com.jason.littlelives.ui.component.RegularText
import com.jason.littlelives.ui.feature.event.view.EventListScreen
import com.jason.littlelives.ui.feature.event.viewModel.EventListViewModel
import com.jason.littlelives.ui.feature.home.viewModel.HomeViewModel
import com.jason.littlelives.ui.feature.root.AppNavigator

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScreen(
    navigator: AppNavigator,
    homeViewModel: HomeViewModel,
    eventListViewModel: EventListViewModel
) {
    val pagerState = rememberPagerState()
    val context = LocalContext.current

    val navigationItems = listOf(
        BottomNavItem.News,
        BottomNavItem.CheckIn,
        BottomNavItem.Inbox,
        BottomNavItem.Portfolio,
        BottomNavItem.More
    )

    BackHandler(true) {
        (context as Activity).finish()
    }

    Scaffold(
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding(),
        bottomBar = {
            CustomBottomNavigation(
                items = navigationItems,
                pagerState = pagerState
            )
        }
    ) { paddingValues ->
        HorizontalPager(
            modifier = Modifier
                .padding(paddingValues),
            count = navigationItems.size,
            state = pagerState,
            userScrollEnabled = false
        ) { index ->
            when (index) {
                0 -> {
                    EventListScreen(
                        navigator = navigator,
                        eventListViewModel = eventListViewModel
                    )
                }
                1 -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        RegularText(content = "Check In")
                    }
                }
                2 -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        RegularText(content = "Inbox")
                    }
                }
                3 -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        RegularText(content = "Portfolio")
                    }
                }
                4 -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        RegularText(content = "More")
                    }
                }
            }
        }
    }
}