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

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Day_01
{
    public static void main(String[] args) throws IOException
    {
        File file = new File("src/rsc/Day_01.txt");
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

        List<Integer> leftLocationIDs = new ArrayList<>();
        List<Integer> rightLocationIDs = new ArrayList<>();

        for (String line : lines)
        {
            if (line.isBlank())
            {
                continue;
            }

            line = line.replaceAll("\\s+", " ");
            String[] parts = line.split(" ");

            String leftLocationID = parts[0];
            String rightLocationID = parts[1];

            int leftLID = Integer.parseInt(leftLocationID);
            int rightLID = Integer.parseInt(rightLocationID);

            leftLocationIDs.add(leftLID);
            rightLocationIDs.add(rightLID);
        }

        Collections.sort(leftLocationIDs);
        Collections.sort(rightLocationIDs);

        for (int i = 0; i < leftLocationIDs.size(); i++)
        {
            int leftLocationID = leftLocationIDs.get(i);
            int rightLocationID = rightLocationIDs.get(i);

            int maxLocationID = Math.max(leftLocationID, rightLocationID);
            int minLocationID = Math.min(leftLocationID, rightLocationID);

            sum += (maxLocationID - minLocationID);
        }

        return sum;
    }

    public static long part2(List<String> lines)
    {
        long sum = 0;

        List<Integer> leftLocationIDs = new ArrayList<>();
        List<Integer> rightLocationIDs = new ArrayList<>();

        for (String line : lines)
        {
            if (line.isBlank())
            {
                continue;
            }

            line = line.replaceAll("\\s+", " ");
            String[] parts = line.split(" ");

            String leftLocationID = parts[0];
            String rightLocationID = parts[1];

            int leftLID = Integer.parseInt(leftLocationID);
            int rightLID = Integer.parseInt(rightLocationID);

            leftLocationIDs.add(leftLID);
            rightLocationIDs.add(rightLID);
        }

        for (int leftLocationID : leftLocationIDs)
        {
            long count = rightLocationIDs.stream().filter(rightLocationID -> leftLocationID == rightLocationID).count();
            sum += (leftLocationID * count);
        }

        return sum;
    }
}
