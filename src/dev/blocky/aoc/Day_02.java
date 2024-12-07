/**
 * Solutions for Advent of Code 2024.
 * Copyright (C) 2024 BlockyDotJar (aka. Dominic R.)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package dev.blocky.aoc;

import dev.blocky.aoc.annotations.Unsolved;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Day_02
{
    public static void main(String[] args) throws IOException
    {
        File file = new File("src/rsc/Day_02.txt");
        Path path = file.toPath();
        List<String> fileContent = Files.readAllLines(path, UTF_8);

        // Part 1 of the Challenge.
        System.out.println(part1(fileContent));

        // Part 2 of the Challenge.
        System.out.println(part2(fileContent));
    }

    public static int part1(List<String> lines)
    {
        int sum = 0;

        for (String line : lines)
        {
            if (line.isBlank())
            {
                continue;
            }

            String[] parts = line.split(" ");
            List<Integer> levels = Arrays.stream(parts).map(Integer::parseInt).toList();

            if (!isMonotonic(levels))
            {
                continue;
            }

            int levelCount = levels.size();

            for (int i = 0; i < levelCount; i++)
            {
                int currentLevel = levels.get(i);

                if ((i + 1) == levelCount)
                {
                    sum++;
                    break;
                }

                int nextLevel = levels.get(i + 1);

                int maxLevel = Math.max(currentLevel, nextLevel);
                int minLevel = Math.min(currentLevel, nextLevel);

                if ((maxLevel - minLevel) > 3)
                {
                    break;
                }

                if (currentLevel == nextLevel)
                {
                    break;
                }
            }
        }

        return sum;
    }

    @Unsolved(willSolveInFuture = false)
    public static int part2(@SuppressWarnings("unused") List<String> lines)
    {
        return -1;
    }

    public static boolean isMonotonic(List<Integer> levels)
    {
        int levelCount = levels.size();
        boolean inc = true;
        boolean dec = true;

        for (int i = 0; i < levelCount - 1; i++)
        {
            int currentLevel = levels.get(i);
            int nextLevel = levels.get(i + 1);

            if (currentLevel > nextLevel)
            {
                inc = false;
            }

            if (currentLevel < nextLevel)
            {
                dec = false;
            }
        }

        return inc || dec;
    }
}
