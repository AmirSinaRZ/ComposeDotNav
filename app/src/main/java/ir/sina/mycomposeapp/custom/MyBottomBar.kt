package ir.sina.mycomposeapp.custom

import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AnimatedBottomNavigation(
    items: List<BottomNavItem>,
    selectedItem: String,
    onItemSelected: (String) -> Unit,
    containerColor: Color = Color.White,
    textColor: Color = Color.White,
    iconColor: Color = Color.White
) {
//    BottomAppBar(
//        containerColor = containerColor,
//        modifier = Modifier
//            .fillMaxWidth()
//    ) {
//        items.forEach { item ->
//            val isSelected = item.route == selectedItem
//            NavigationBarItem(
//                selected = isSelected,
//                onClick = { onItemSelected(item.route) },
//                icon = {
//                    AnimatedContent(isSelected = isSelected, item = item, textColor = textColor, iconColor = iconColor)
//                }
//            )
//        }
//    }
    BottomAppBar(
        containerColor = containerColor
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(containerColor),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEach { item ->
                val isSelected = item.route == selectedItem
                CustomNavigationItem(
                    item = item,
                    isSelected = isSelected,
                    onClick = { onItemSelected(item.route) },
                    textColor,
                    iconColor
                )
            }
        }
    }
}



@Composable
fun CustomNavigationItem(
    item: BottomNavItem,
    isSelected: Boolean,
    onClick: () -> Unit,
    textColor: Color,
    iconColor: Color
) {
    val transition = updateTransition(targetState = isSelected, label = "IconTitleTransition")

    val alpha by transition.animateFloat(label = "Alpha") { selected ->
        if (selected) 1f else 0f
    }
    val offsetY by transition.animateDp(label = "OffsetY") { selected ->
        if (selected) 0.dp else 20.dp
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { onClick() }
            .padding(8.dp)
    ) {
        if (isSelected) {
            // Title with dot when selected
            Text(
                text = item.title,
                color = textColor,
                fontSize = 16.sp,
                modifier = Modifier
                    .alpha(alpha)
                    .offset(y = offsetY)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Box(
                Modifier
                    .size(5.dp)
                    .background(textColor, shape = CircleShape)
            )
        } else {
            // Icon only when not selected
            Icon(
                imageVector = item.icon,
                contentDescription = item.title,
                tint = iconColor,
                modifier = Modifier
                    .size(26.dp)
                    .alpha(1f - alpha)
            )
        }
    }
}


@Composable
fun AnimatedContent(isSelected: Boolean, item: BottomNavItem, textColor: Color, iconColor: Color) {
    val transition = updateTransition(targetState = isSelected, label = "IconTitleTransition")

    val alpha by transition.animateFloat(label = "Alpha") { selected ->
        if (selected) 1f else 0f
    }
    val offsetY by transition.animateDp(label = "OffsetY") { selected ->
        if (selected) 0.dp else 20.dp
    }

    if (isSelected) {
        // When selected, show the title with a dot below
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(4.dp)
                .alpha(alpha)
                .offset(y = offsetY)
        ) {
            Text(text = item.title, style = MaterialTheme.typography.titleMedium, color = textColor)
            Spacer(modifier = Modifier.height(4.dp))
            Box(
                Modifier
                    .size(4.dp)
                    .background(textColor, shape = CircleShape)
            )
        }
    } else {
        // When not selected, show the icon only
        Icon(
            imageVector = item.icon,
            contentDescription = item.title,
            tint = iconColor,
            modifier = Modifier
                .size(24.dp)
                .alpha(1f - alpha)
        )
    }
}
