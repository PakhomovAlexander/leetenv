package com.apakhomov.leetenv.plugins;

import java.io.IOException;
import java.util.Arrays;

public class LeetupLeetcodeClient {
    private final String basePath;

    public LeetupLeetcodeClient(String basePath) {
        this.basePath = basePath;
    }

    void pick(int num) {
        try {
            new ProcessBuilder()
                    .command("cd", basePath)
                    .command("leetup", "pick", String.valueOf(num), "--lang=java")
                    .start()
                    .waitFor();

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
