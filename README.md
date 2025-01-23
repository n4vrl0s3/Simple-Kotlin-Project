# Navigation Drawer APP

This repository contains the source code for an Android application demonstrating the implementation of a Navigation Drawer using the Kotlin language within the Android Studio environment. This project showcases how to create a user-friendly and intuitive navigation experience.

<hr><br>

## Purpose of This Repository

To showcase the integration of a Navigation Drawer with Fragments to create a flexible and modular user interface within an Android application developed with Kotlin.

<hr><br>

## Demo

Below is a demonstration of the main function of the Navigation Drawer App:

```kotlin
// filepath: /home/guan/Documents/Code/Simple-Kotlin-Project/app/src/main/java/com/example/navigationdrawer/MainActivity.kt
package com.example.navigationdrawer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)

        // Setup the navigation drawer
        navView.setNavigationItemSelectedListener { menuItem ->
            // Handle navigation view item clicks here.
            menuItem.isChecked = true
            drawerLayout.closeDrawers()
            true
        }
    }
}
```

<hr><br>

## Releases

You can find the latest releases [here](https://github.com/guanshiyin28/Simple-Kotlin-Project/releases).

<hr><br>

## Features

- Navigation Drawer with multiple menu items
- Fragment integration for modular UI
- Smooth and intuitive navigation
- Responsive design for various screen sizes

<hr><br>

## Technologies Used

- Kotlin
- Android Studio
- XML for layout design

<hr><br>

## Project Setup

1. **Ensure you have Android Studio installed on your machine.**
2. **Clone this Repository**

```bash
git clone https://github.com/guanshiyin28/Navigation-Drawer.git
```

3. **Open the project in Android Studio**
4. **Build the project to download dependencies**

<hr><br>

## Steps to Run

1. **Open the project in Android Studio**
2. **Build the project to download dependencies**
3. **Run the project on an emulator or physical device**

<hr><br>

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

<hr><br>

<div align="center">
  <a href="https://www.instagram.com/guanshiyin_/">
  <img src="https://capsule-render.vercel.app/api?type=waving&height=200&color=100:FF0000,20:F0F0F0&section=footer&reversal=false&textBg=false&fontAlignY=50&descAlign=48&descAlignY=59"/>
  </a>
</div>
