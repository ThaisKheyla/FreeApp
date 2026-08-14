package com.projeto.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.freeapp.R
import com.example.freeapp.ui.theme.PrimaryBlue


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
                    contentDescription = "Home"
                )
            },
            label = {
                Text(
                    text = "Home",
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
                    contentDescription = "Calendar"
                )
            },
            label = {
                Text("Calendar")
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
                    contentDescription = "Chat"
                )
            },
            label = {
                Text( "Chat")
            }
        )

        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.fi_rr_profile),
                    contentDescription = "Profile"
                )
            },
            label = {
                Text("Profile")
            }
                )
            }
    }

@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
    BottomNavigationBar()
}
