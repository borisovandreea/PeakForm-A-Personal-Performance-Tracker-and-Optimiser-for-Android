# PeakForm-A-Personal-Performance-Tracker-and-Optimiser-for-Android

PeakForm is a performance-tracking application designed to help users visualize their daily balance through a weighted scoring algorithm. This version represents the **Initial Vertical Slice**, focusing on data entry, transparent scoring, and visual feedback.

---

## How to Operate the App

### 1. The Dashboard (Home Screen)
When you open the app, you are greeted by the **Life Balance Score**.
* **Big Number:** This is your overall performance score (0-100), calculated based on your activities, their quality, and their priority.
* **Dynamic Colors:** The score and bars change color based on performance:
    * 🔴 **Red (< 50%):** Needs immediate attention.
    * 🟡 **Yellow (50-79%):** On the right track, but room for improvement.
    * 🟢 **Green (> 80%):** High alignment with goals and priority.
* **Horizontal Chart:** Displays your progress for each specific activity relative to the goals you set.

### 2. Logging an Activity
To add data to your dashboard:
1.  Tap the **"ADD ANOTHER ACTIVITY"** button.
2.  **Activity Name:** Enter what you did (e.g., "Morning Run" or "Coding").
3.  **Domain:** Select the category from the dropdown (Work, Health, Social, etc.).
4.  **Goal vs. Actual:** Enter how many minutes you *intended* to spend versus how many you *actually* spent.
5.  **Quality of Focus:** Use the slider (0-10) to rate how concentrated you were during the task.
6.  **Personal Priority & Importance:** Use the slider (0-10) to tell the app how much this specific activity matters to your life. **High priority activities affect your overall score more than low priority ones.**
7.  Tap **"ADD ACTIVITY"** to save and return to the dashboard.

### 3. Managing Data
* **Refresh:** The chart and score update instantly every time you add a new activity.
* **Reset:** Use the **"Reset Dashboard"** button at the bottom of the home screen to wipe all data and start a new tracking period.

---

## Technical "Vertical Slice" Details
* **Frontend:** XML-based layouts with `HorizontalBarChart` (MPAndroidChart).
* **Backend:** Room Persistence Library (SQLite) for local data storage.
* **Architecture:** MVVM (Model-View-ViewModel) to ensure data stays synced across screens.
* **Logic:** The score is a weighted average: 
    $$Score = \frac{\sum (Completion \times Quality \times Weight)}{\sum Weight}$$

---

## MVP Status
- Data Entry System
- Transparent Weighted Scoring
- Visual Performance History 
- Persistent Storage

