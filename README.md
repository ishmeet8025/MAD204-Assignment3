# MAD204 – Assignment 3  
## Notes & Media Manager App  
### Author: Ishmeet Singh  
### Package: com.ishmeetsingh.assignment3

This Android app allows users to create, edit, delete, and manage notes with optional images/videos. Notes can be marked as favorites, searched, imported/exported as JSON, and a reminder notification can be triggered through a background service. The app includes a settings page for username, dark mode, and export directory preferences.

---

## ✔ Features
- Add, edit, delete notes  
- Attach image/video to notes  
- Mark notes as favorite  
- Search notes  
- JSON import/export  
- Choose export folder  
- SharedPreferences for username & theme  
- Reminder notification (Service)  
- RecyclerView UI  

---

## ✔ Technologies Used
- Java  
- Room Database  
- SharedPreferences  
- RecyclerView  
- Services & Notifications  
- JSON (Gson)  
- Internal Storage  

---

## ✔ Project Structure
- `MainActivity` – Notes list  
- `EditNoteActivity` – Create/edit notes  
- `SettingsActivity` – Preferences  
- `NoteAdapter` – RecyclerView  
- `Room DB` – Note entity, DAO, database  
- `Utils` – JSON, files, preferences, notifications  
- `ReminderService` – Delayed notification  

---

## ✔ GitHub Requirements Completed
- **7 commits** (scaffold, Gradle, Room, Adapter, Activities, Service, Layouts)  
- **4 Pull Requests** (Room, UI, Settings, Final features)

---

### This project completes all requirements for MAD204 Assignment 3.
