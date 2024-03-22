package presentation.designsystem

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.zIndex
import kotlinx.coroutines.launch
import presentation.theme.CORNER_RADIUS_4
import presentation.theme.FONT_16
import presentation.theme.PADDING_2
import presentation.theme.ZERO_DP
import presentation.theme.gray400

@Composable
fun TopAppBar(title: String, onBack: () -> Unit) {
    TopAppBar(
        contentColor = Color.White,
        backgroundColor = Color.Transparent,
        elevation = ZERO_DP,
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .align(Alignment.CenterVertically)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center),
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = FONT_16,
                textAlign = TextAlign.Center
            )
            IconButton(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .fillMaxHeight(),
                onClick = { onBack.invoke() }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back Button"
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabBar(modifier: Modifier = Modifier, pages: List<String>,
           contentColors: List<Color>, onTabBarChange: (String) -> Unit) {

    val pagerState = rememberPagerState(pageCount = { pages.size })
    val coroutineScope = rememberCoroutineScope()
    var selectedTabIndex by remember {
        mutableStateOf(0)
    }

    TabRow(
        modifier = modifier
            .clip(shape = RoundedCornerShape(CORNER_RADIUS_4)),
        selectedTabIndex = selectedTabIndex,
        indicator = { tabPositions ->
            Box(
                modifier = Modifier
                    .tabIndicatorOffset(tabPositions[selectedTabIndex])
                    .zIndex(-1f)
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(PADDING_2)
                    .background(
                        color = contentColors[selectedTabIndex].copy(alpha = 0.12f),
                        shape = RoundedCornerShape(CORNER_RADIUS_4)
                    )
            )
        },
        contentColor = MaterialTheme.colors.secondary,
        divider = {}
    ) {
        pages.forEachIndexed { index, title ->
            Tab(
                text = {
                    Text(
                        modifier = Modifier
                                .semantics { contentDescription = "Tab Title" },
                        text = title,
                        color =
                            if (selectedTabIndex == index)
                                contentColors[selectedTabIndex]
                            else
                                gray400,
                        fontWeight = FontWeight.Bold
                    )
                },
                selected = pagerState.currentPage == index,
                onClick = {
                    coroutineScope.launch {
                        selectedTabIndex = index
                        onTabBarChange(pages[selectedTabIndex])
                    }
                },
            )
        }
    }
}