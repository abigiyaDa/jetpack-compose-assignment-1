package com.moblieappproject.mobileindividualassignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moblieappproject.mobileindividualassignment.ui.theme.MobileIndividualAssignmentTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobileIndividualAssignmentTheme {
                Scaffold(
                    topBar = {
                        CenterAlignedTopAppBar(title = { Text("Course Catalog") })
                    }
                ) { padding ->
                    CourseListScreen(
                        modifier = Modifier.padding(padding),
                        courses = sampleCourses // state hoisting
                    )
                }
            }
        }
    }
}



@Composable
fun CourseListScreen(
    modifier: Modifier = Modifier,
    courses: List<Map<String, String>>
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        items(courses) { course ->
            CourseCard(course = course)
        }
    }
}

@Composable
fun CourseCard(course: Map<String, String>) {
    var isExpanded by rememberSaveable { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 1500,
                    easing = LinearOutSlowInEasing
                )
            )// animation
    ) {
        Column(
            modifier = Modifier
                .clickable { isExpanded = !isExpanded }
                .padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {

                Column(modifier = Modifier.weight(1f)) {
                    Text(course["title"] ?: "",
                        style = MaterialTheme.typography.headlineSmall)

                    Spacer(Modifier.height(4.dp))

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(course["code"] ?: "",
                            style = MaterialTheme.typography.bodyLarge)

                        Text("${course["credits"]} Credits",
                            style = MaterialTheme.typography.bodyLarge)

                    }
                }
                IconButton(onClick = { isExpanded = !isExpanded }) {
                    Icon(
                        imageVector = if (isExpanded)
                            Icons.Filled.ExpandLess
                        else
                            Icons.Filled.ExpandMore,

                        contentDescription = if (isExpanded) "Collapse" else "Expand"
                    )
                }
            }

            if (isExpanded) {
                Spacer(Modifier.height(8.dp))

                Text("Description: ${course["description"]}",
                    style = MaterialTheme.typography.bodyLarge)

                Text("Prerequisites: ${course["prerequisites"]}",
                    style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LightPreview() {
    MobileIndividualAssignmentTheme {
        CourseListScreen(courses = sampleCourses)
    }
}

@Preview(uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DarkPreview() {
    MobileIndividualAssignmentTheme {
        CourseListScreen(courses = sampleCourses)
    }
}
private val sampleCourses = listOf(
    mapOf(
        "title" to "Fundamentals to AI",
        "code" to "CS320",
        "credits" to "3",
        "description" to "Basics of artificial intelligence and machine learning.",
        "prerequisites" to "Math for CS"
    ),
    mapOf(
        "title" to "Fundamentals to Cyber Security",
        "code" to "CS330",
        "credits" to "3",
        "description" to "Intro to cybersecurity threats, attacks, and protections.",
        "prerequisites" to "Networking Basics"
    ),
    mapOf(
        "title" to "Computer Networks",
        "code" to "CS210",
        "credits" to "3",
        "description" to "Understand data transmission and communication protocols.",
        "prerequisites" to "Digital Logic"
    ),
    mapOf(
        "title" to "Human-Computer Interaction",
        "code" to "CS215",
        "credits" to "3",
        "description" to "Designing intuitive and user-friendly interfaces.",
        "prerequisites" to "Software Design"
    ),
    mapOf(
        "title" to "Databases",
        "code" to "CS220",
        "credits" to "3",
        "description" to "Study SQL, database design, and data models.",
        "prerequisites" to "CS103"
    ),
    mapOf(
        "title" to "Software Engineering",
        "code" to "CS310",
        "credits" to "4",
        "description" to "Software lifecycle, project planning, and testing.",
        "prerequisites" to "CS201"
    ),
    mapOf(
        "title" to "Mobile App Development",
        "code" to "CS305",
        "credits" to "3",
        "description" to "Develop mobile apps using native and cross-platform tools.",
        "prerequisites" to "CS101"
    ),
    mapOf(
        "title" to "Operating Systems",
        "code" to "CS204",
        "credits" to "4",
        "description" to "Understand process, memory, and file management.",
        "prerequisites" to "CS103"
    ),
    mapOf(
        "title" to "Computer Graphics",
        "code" to "CS303",
        "credits" to "3",
        "description" to "Explore 2D and 3D rendering concepts.",
        "prerequisites" to "CS201"
    ),
    mapOf(
        "title" to "Algorithms & Data Structures",
        "code" to "CS250",
        "credits" to "3",
        "description" to "Design and analyze efficient algorithms and data structures.",
        "prerequisites" to "CS102"
    ),
    mapOf(
        "title" to "Web Development",
        "code" to "CS340",
        "credits" to "3",
        "description" to "Building modern web applications with REST and frameworks.",
        "prerequisites" to "CS101"
    ),
    mapOf(
        "title" to "Machine Learning",
        "code" to "CS350",
        "credits" to "3",
        "description" to "Supervised and unsupervised learning algorithms.",
        "prerequisites" to "CS320"
    ),
    mapOf(
        "title" to "Machine Learning",
        "code" to "CS350",
        "credits" to "3",
        "description" to "Supervised and unsupervised learning algorithms.",
        "prerequisites" to "CS320"
    ),
    mapOf(
        "title" to "Machine Learning",
        "code" to "CS350",
        "credits" to "3",
        "description" to "Supervised and unsupervised learning algorithms.",
        "prerequisites" to "CS320"
    )

)