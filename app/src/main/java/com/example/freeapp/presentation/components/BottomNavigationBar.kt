package com.example.freeapp.presentation.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.freeapp.R
import com.example.freeapp.presentation.theme.PrimaryBlue

@Composable
fun BottomNavigationBar() {

    NavigationBar {

        NavigationBarItem(
            selected = true,
            onClick = { },
            icon = {
                Icon(
                    painter = painterResource(
                        id = R.drawable.fi_rr_home
                    ),
                    tint = PrimaryBlue,
                    contentDescription = stringResource(
                        R.string.bottom_nav_home
                    )
                )
            },
            label = {
                Text(
                    text = stringResource(
                        R.string.bottom_nav_home
                    ),
                    color = PrimaryBlue
                )
            }
        )

        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = {
                Icon(
                    painter = painterResource(
                        id = R.drawable.fi_rr_calendar
                    ),
                    contentDescription = stringResource(
                        R.string.bottom_nav_calendar
                    )
                )
            },
            label = {
                Text(
                    text = stringResource(
                        R.string.bottom_nav_calendar
                    )
                )
            }
        )

        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = {
                Icon(
                    painter = painterResource(
                        id = R.drawable.fi_rr_message
                    ),
                    contentDescription = stringResource(
                        R.string.bottom_nav_chat
                    )
                )
            },
            label = {
                Text(
                    text = stringResource(
                        R.string.bottom_nav_chat
                    )
                )
            }
        )

        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = {
                Icon(
                    painter = painterResource(
                        id = R.drawable.fi_rr_profile
                    ),
                    contentDescription = stringResource(
                        R.string.bottom_nav_profile
                    )
                )
            },
            label = {
                Text(
                    text = stringResource(
                        R.string.bottom_nav_profile
                    )
                )
            }
        )
    }
}
