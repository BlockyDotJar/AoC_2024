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

public class Day_05
{
    public static void main(String[] args) throws IOException
    {
        File file = new File("src/rsc/Day_05.txt");
        Path path = file.toPath();
        List<String> fileContent = Files.readAllLines(path, UTF_8);

        // Part 1 of the Challenge.
        System.out.println(part1(fileContent));

        // Part 2 of the Challenge.
        System.out.println(part2(fileContent));
    }

    @Unsolved(willSolveInFuture = false)
    public static int part1(@SuppressWarnings("unused") List<String> lines)
    {
        return -1;
    }

    @Unsolved(willSolveInFuture = false)
    public static int part2(@SuppressWarnings("unused") List<String> lines)
    {
        return -1;
    }
}
