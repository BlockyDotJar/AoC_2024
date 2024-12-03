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
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Day_03
{
    public static void main(String[] args) throws IOException
    {
        File file = new File("src/rsc/Day_03.txt");
        Path path = file.toPath();
        List<String> fileContent = Files.readAllLines(path, UTF_8);

        // Part 1 of the Challenge.
        System.out.println(part1(fileContent));

        // Part 2 of the Challenge.
        System.out.println(part2(fileContent));
    }

    public static long part1(List<String> lines)
    {
        long sum = 0;

        for (String line : lines)
        {
            if (line.isBlank())
            {
                continue;
            }

            Pattern pattern = Pattern.compile("mul\\(\\d+,\\d+\\)");
            Matcher matcher = pattern.matcher(line);

            List<String> results = matcher.results().map(MatchResult::group).toList();

            sum += results.stream().mapToInt(result ->
            {
                int resultLength = result.length();

                String mulVal = result.substring(4, resultLength - 1);
                String[] mulValVals = mulVal.split(",");

                String firstNum = mulValVals[0];
                String secondNum = mulValVals[1];

                int fNum = Integer.parseInt(firstNum);
                int sNum = Integer.parseInt(secondNum);

                return fNum * sNum;
            }).sum();
        }

        return sum;
    }

    public static int part2(List<String> lines)
    {
        int sum = 0;

        boolean doMultiply = true;

        for (String line : lines)
        {
            if (line.isBlank())
            {
                continue;
            }

            Pattern pattern = Pattern.compile("(do\\(\\)|don't\\(\\)|mul\\(\\d+,\\d+\\))");
            Matcher matcher = pattern.matcher(line);

            List<String> results = matcher.results().map(MatchResult::group).toList();

            for (String result : results)
            {
                if (result.equals("do()"))
                {
                    doMultiply = true;
                    continue;
                }

                if (result.equals("don't()"))
                {
                    doMultiply = false;
                    continue;
                }

                if (doMultiply)
                {
                    int resultLength = result.length();

                    String mulVal = result.substring(4, resultLength - 1);
                    String[] mulValVals = mulVal.split(",");

                    String firstNum = mulValVals[0];
                    String secondNum = mulValVals[1];

                    int fNum = Integer.parseInt(firstNum);
                    int sNum = Integer.parseInt(secondNum);

                    sum += (fNum * sNum);
                }
            }
        }

        return sum;
    }
}
