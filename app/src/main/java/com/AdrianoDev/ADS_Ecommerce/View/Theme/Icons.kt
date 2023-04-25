package com.AdrianoDev.ADS_Ecommerce.View.Theme
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.PathNode
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.materialPath

public val InstaIcon: ImageVector
    get() {
        if (InstagramIcon != null) {
            return InstagramIcon!!
        }
        InstagramIcon = materialIcon(name = "InstaIcon") {
            materialPath {
                moveTo(17.5f, 2.5f)
                horizontalLineTo(6.5f)
                arcTo(4.5f, 4.5f, 0.0f, false, false, 2.0f, 7.0f)
                verticalLineTo(17.0f)
                arcTo(4.5f, 4.5f, 0.0f, false, false, 6.5f, 21.5f)
                horizontalLineTo(17.5f)
                arcTo(4.5f, 4.5f, 0.0f, false, false, 22.0f, 17.0f)
                verticalLineTo(7.0f)
                arcTo(4.5f, 4.5f, 0.0f, false, false, 17.5f, 2.5f)
                close()
                moveTo(12.0f, 15.5f)
                arcTo(3.5f, 3.5f, 0.0f, true, true, 12.0f, 8.5f)
                arcTo(3.5f, 3.5f, 0.0f, false, true, 12.0f, 15.5f)
                close()
                moveTo(17.5f, 5.5f)
                arcToRelative(2.5f, 2.5f, 0.0f, false, true, -2.5f, 2.5f)
                arcToRelative(2.5f, 2.5f, 0.0f, false, true, -2.5f, -2.5f)
                arcToRelative(2.5f, 2.5f, 0.0f, false, true, 2.5f, -2.5f)
                arcToRelative(2.5f, 2.5f, 0.0f, false, true, 2.5f, 2.5f)
                close()
            }
        }
        return InstagramIcon!!
    }

private var InstagramIcon: ImageVector? = null