//package com.example.weatherapp.navigation
//
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.material.*
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.ArrowBack
//import androidx.compose.material.icons.filled.Home
//import androidx.compose.material.icons.filled.Notifications
//import androidx.compose.material.icons.filled.Settings
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Alignment.Companion.CenterHorizontally
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.vector.ImageVector
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavController
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.currentBackStackEntryAsState
//import androidx.navigation.compose.rememberNavController
//import com.example.weatherapp.ui.screens.FindCityScreen
//import com.example.weatherapp.ui.screens.NewMainScreen
//import com.example.weatherapp.ui.screens.WeatherScreen
//
//@OptIn(ExperimentalMaterialApi::class)
//@Composable
//fun Navigation() {
//    val materialBlue700 = Color(0xFF1976D2)
//    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
//    val navController = rememberNavController()
//    Scaffold(
//        bottomBar = {
//            BottomNavigationBar(
//                items = listOf(
//                    BottomNavItem(
//                        name = "Home",
//                        route = "home",
//                        icon = Icons.Default.Home
//                    ),
//                    BottomNavItem(
//                        name = "Chat",
//                        route = "chat",
//                        icon = Icons.Default.Notifications,
//                        badgeCount = 23
//                    ),
//                    BottomNavItem(
//                        name = "Settings",
//                        route = "settings",
//                        icon = Icons.Default.Settings,
//                        badgeCount = 214
//                    ),
//                ),
//                navController = navController,
//                onItemClick = {
//                    navController.navigate(it.route)
//                }
//            ) },
//        scaffoldState = scaffoldState,
//        drawerContent = { Text(text = "drawerContent") },
//        content = {
//            NavigationScreens()
//        },
//        //        bottomBar = { BottomAppBar(backgroundColor = materialBlue700) { Text("BottomAppBar") } }
//    )
//}
//
//
//@Composable
//fun NavigationScreens() {
//    val navController = rememberNavController()
//    NavHost(navController = navController, startDestination = "home") {
//
//        composable(route = Screens.FindCity.route) {
//            FindCityScreen(navController = navController)
//        }
//        composable(route = Screens.Weather.route) {
//            WeatherScreen()
//        }
//        composable(route = Screens.MainScreen.route) {
//            NewMainScreen()
//        }
//        composable("home") {
//            HomeScreen()
//        }
//        composable("chat") {
//            ChatScreen()
//        }
//        composable("settings") {
//            SettingsScreen()
//        }
//
//    }
//}
//
//@ExperimentalMaterialApi
//@Composable
//fun BottomNavigationBar(
//    items: List<BottomNavItem>,
//    navController: NavController,
//    modifier: Modifier = Modifier,
//    onItemClick: (BottomNavItem) -> Unit
//) {
//    val backStackEntry = navController.currentBackStackEntryAsState()
//    BottomNavigation(
//        modifier = modifier,
//        backgroundColor = Color.DarkGray,
//        elevation = 5.dp
//    ) {
//        items.forEach { item ->
//            val selected = item.route == backStackEntry.value?.destination?.route
//            BottomNavigationItem(
//                selected = selected,
//                onClick = { onItemClick(item) },
//                selectedContentColor = Color.Green,
//                unselectedContentColor = Color.Gray,
//                icon = {
//                    Column(horizontalAlignment = CenterHorizontally) {
//                        if(item.badgeCount > 0) {
//                            BadgeBox(
//                                badgeContent = {
//                                    Text(text = item.badgeCount.toString())
//                                }
//                            ) {
//                                Icon(
//                                    imageVector = item.icon,
//                                    contentDescription = item.name
//                                )
//                            }
//                        } else {
//                            Icon(
//                                imageVector = item.icon,
//                                contentDescription = item.name
//                            )
//                        }
//                        if(selected) {
//                            Text(
//                                text = item.name,
//                                textAlign = TextAlign.Center,
//                                fontSize = 10.sp
//                            )
//                        }
//                    }
//                }
//            )
//        }
//    }
//}
//
//@Composable
//fun HomeScreen() {
//    Box(
//        modifier = Modifier.fillMaxSize(),
//        contentAlignment = Alignment.Center
//    ) {
//        Text(text = "Home screen")
//    }
//}
//
//@Composable
//fun ChatScreen() {
//    Box(
//        modifier = Modifier.fillMaxSize(),
//        contentAlignment = Alignment.Center
//    ) {
//        Text(text = "Chat screen")
//    }
//}
//
//@Composable
//fun SettingsScreen() {
//    Box(
//        modifier = Modifier.fillMaxSize(),
//        contentAlignment = Alignment.Center
//    ) {
//        Text(text = "Settings screen")
//    }
//}
//
//data class BottomNavItem(
//    val name: String,
//    val route: String,
//    val icon: ImageVector,
//    val badgeCount: Int = 0
//)