package ru.darkalive.LightLMS.config;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

@Component
public class BasicFileCreating {

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() { checkDirectories(); }

    private void checkDirectories() {

        List<String> directories = List.of("groups", "subjects");
        directories.forEach(this::checkDirectory);
    }

    private void checkDirectory(String directoryName) {

        File directory = new File("./" + directoryName);
        if (!directory.exists()) {
            printMessage("Директория " + directoryName + " не найдена, идет создание");
            if (directory.mkdir())
                printMessage("Директория " + directoryName + " успешно создана");
            else
                printMessage("При создании директории " + directoryName + " произошла ошибка");
        }
    }

    private void printMessage(String message) { System.out.println("[MultimediaAI - BasicDirs]\t" + message); }
}
