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
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Day_06
{
    public static void main(String[] args) throws IOException
    {
        File file = new File("src/rsc/Day_06.txt");
        Path path = file.toPath();
        List<String> fileContent = Files.readAllLines(path, UTF_8);

        // Part 1 of the Challenge.
        System.out.println(part1(fileContent));

        // Part 2 of the Challenge.
        System.out.println(part2(fileContent));
    }

    public static long part1(List<String> lines)
    {
        return getDistinctPositions(lines, true, false, false, false);
    }

    public static long getDistinctPositions(List<String> lines, boolean up, boolean down, boolean left, boolean right)
    {
        int lineCount = lines.size();

        for (int i = 0; i < lineCount; i++)
        {
            String line = lines.get(i);

            if (line.isBlank())
            {
                continue;
            }

            if (!line.contains("^"))
            {
                continue;
            }

            int currentPath = i;
            int currentPosition = line.indexOf("^");

            if (up)
            {
                for (int y = (currentPath - 1); y >= 0; y--)
                {
                    String lastPath = lines.get(y);
                    char[] chars = lastPath.toCharArray();
                    char guardPositionFFW = chars[currentPosition];

                    if (guardPositionFFW == '#')
                    {
                        String guardMark = line.replace("^", "X");
                        lines.set(currentPath, guardMark);

                        currentPath = y + 1;

                        String nextPath = lines.get(currentPath);

                        StringBuilder guardedNextPath = new StringBuilder(nextPath);
                        guardedNextPath.setCharAt(currentPosition, '^');

                        lines.set(currentPath, guardedNextPath.toString());

                        return getDistinctPositions(lines, false, false, false, true);
                    }

                    StringBuilder guardedLastPath = new StringBuilder(lastPath);
                    guardedLastPath.setCharAt(currentPosition, 'X');

                    lines.set(y, guardedLastPath.toString());

                    if (y == 0)
                    {
                        long guardMarks = lines.stream()
                                .mapToLong(l -> l.chars().filter(ch -> ch == 'X').count())
                                .sum();

                        return guardMarks + 1;
                    }
                }
            }

            if (down)
            {
                for (int y = (currentPath + 1); y < lineCount; y++)
                {
                    String nextPath = lines.get(y);
                    char[] chars = nextPath.toCharArray();
                    char guardPositionFFW = chars[currentPosition];

                    if (guardPositionFFW == '#')
                    {
                        String guardMark = line.replace("^", "X");
                        lines.set(currentPath, guardMark);

                        currentPath = y - 1;

                        String lastPath = lines.get(currentPath);

                        StringBuilder guardedNextPath = new StringBuilder(lastPath);
                        guardedNextPath.setCharAt(currentPosition, '^');

                        lines.set(currentPath, guardedNextPath.toString());

                        return getDistinctPositions(lines, false, false, true, false);
                    }

                    StringBuilder guardedNextPath = new StringBuilder(nextPath);
                    guardedNextPath.setCharAt(currentPosition, 'X');

                    lines.set(y, guardedNextPath.toString());

                    if ((y + 1) == lineCount)
                    {
                        long guardMarks = lines.stream()
                                .mapToLong(l -> l.chars().filter(ch -> ch == 'X').count())
                                .sum();

                        return guardMarks + 1;
                    }
                }
            }

            if (right)
            {
                String guardPath = line.substring(currentPosition + 1);
                int guardDistance = guardPath.indexOf("#");

                if (guardDistance == -1)
                {
                    long guardMarks = lines.stream()
                            .mapToLong(l -> l.chars().filter(ch -> ch == 'X').count())
                            .sum();

                    return guardMarks + 1;
                }

                String guardMark = line.replace("^", "X");
                lines.set(currentPath, guardMark);

                int lastPosition = currentPosition;
                currentPosition = (currentPosition + guardDistance);

                StringBuilder guardedNextPath = new StringBuilder(guardMark);
                guardedNextPath.setCharAt(currentPosition, '^');

                String guardedPath = guardedNextPath.toString();

                lines.set(currentPath, guardedPath);

                for (int y = (lastPosition + 1); y < currentPosition; y++)
                {
                    StringBuilder markedPath = new StringBuilder(guardedPath);
                    markedPath.setCharAt(y, 'X');

                    guardedPath = markedPath.toString();
                }

                lines.set(currentPath, guardedPath);

                return getDistinctPositions(lines, false, true, false, false);
            }

            if (left)
            {
                String guardPath = line.substring(0, currentPosition - 1);
                int guardDistance = guardPath.lastIndexOf("#");

                if (guardDistance == -1)
                {
                    long guardMarks = lines.stream()
                            .mapToLong(l -> l.chars().filter(ch -> ch == 'X').count())
                            .sum();

                    return guardMarks + 1;
                }

                String guardMark = line.replace("^", "X");
                lines.set(currentPath, guardMark);

                int lastPosition = currentPosition;
                currentPosition = (guardDistance + 1);

                StringBuilder guardedNextPath = new StringBuilder(guardMark);
                guardedNextPath.setCharAt(currentPosition, '^');

                String guardedPath = guardedNextPath.toString();

                lines.set(currentPath, guardedPath);

                for (int y = (lastPosition - 1); y > currentPosition; y--)
                {
                    StringBuilder markedPath = new StringBuilder(guardedPath);
                    markedPath.setCharAt(y, 'X');

                    guardedPath = markedPath.toString();
                }

                lines.set(currentPath, guardedPath);

                return getDistinctPositions(lines, true, false, false, false);
            }
        }
        return -1;
    }


    @Unsolved(willSolveInFuture = false)
    public static int part2(@SuppressWarnings("unused") List<String> lines)
    {
        return -1;
    }
}
