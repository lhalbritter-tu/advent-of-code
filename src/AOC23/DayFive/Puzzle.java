package AOC23.DayFive;

import AOC23.DayFive.Objects.Seed;
import AOC23.DayFive.Objects.SeedMap;
import Utility.InputManager;

import java.util.*;

public class Puzzle {
    public static void main(String[] args) {
        List<String> input = InputManager.INSTANCE.getAdventOfCodeInput(5, 2023);
        List<String> test = List.of(("seeds: 79 14 55 13\n" +
                "\n" +
                "seed-to-soil map:\n" +
                "50 98 2\n" +
                "52 50 48\n" +
                "\n" +
                "soil-to-fertilizer map:\n" +
                "0 15 37\n" +
                "37 52 2\n" +
                "39 0 15\n" +
                "\n" +
                "fertilizer-to-water map:\n" +
                "49 53 8\n" +
                "0 11 42\n" +
                "42 0 7\n" +
                "57 7 4\n" +
                "\n" +
                "water-to-light map:\n" +
                "88 18 7\n" +
                "18 25 70\n" +
                "\n" +
                "light-to-temperature map:\n" +
                "45 77 23\n" +
                "81 45 19\n" +
                "68 64 13\n" +
                "\n" +
                "temperature-to-humidity map:\n" +
                "0 69 1\n" +
                "1 0 69\n" +
                "\n" +
                "humidity-to-location map:\n" +
                "60 56 37\n" +
                "56 93 4").split("\n"));

        // input = test;

        List<Long> seeds = new ArrayList<>();
        Map<Long, Long> locations = new HashMap<>();

        List<SeedMap> seedToSoilMaps = new ArrayList<>();
        List<SeedMap> soilToFertilizerMaps = new ArrayList<>();
        List<SeedMap> fertilizerToWaterMaps = new ArrayList<>();
        List<SeedMap> waterToLightMaps = new ArrayList<>();
        List<SeedMap> lightToTemperatureMaps = new ArrayList<>();
        List<SeedMap> temperatureToHumidityMaps = new ArrayList<>();
        List<SeedMap> humidityToLocationMaps = new ArrayList<>();

        for (int i = 0; i < input.size(); i++) {
            String line = input.get(i);
            if (line.startsWith("seeds:")) {
                for (String seed : line.split(":")[1].strip().split(" ")) {
                    seeds.add(Long.parseLong(seed.strip()));
                }
            }
            getMap(input, i, "seed-to-soil map:", seedToSoilMaps);
            getMap(input, i, "soil-to-fertilizer map:", soilToFertilizerMaps);
            getMap(input, i, "fertilizer-to-water map:", fertilizerToWaterMaps);
            getMap(input, i, "water-to-light map:", waterToLightMaps);
            getMap(input, i, "light-to-temperature map:", lightToTemperatureMaps);
            getMap(input, i, "temperature-to-humidity map:", temperatureToHumidityMaps);
            getMap(input, i, "humidity-to-location map:", humidityToLocationMaps);
        }

        System.out.println("----------- PART ONE -------------");
        for (Long seed : seeds) {
            locations.put(seed, map(
                    map(
                        map(
                                map(
                                        map(
                                                map(
                                                        map(seed, seedToSoilMaps),
                                                        soilToFertilizerMaps
                                                ),
                                                fertilizerToWaterMaps
                                        ),
                                        waterToLightMaps
                                ),
                                lightToTemperatureMaps
                        ),
                        temperatureToHumidityMaps
                    ),
                    humidityToLocationMaps
                )
            );
            System.out.println(seed + " -> " + locations.get(seed));
        }

        System.out.println(Collections.min(locations.values()));

        // --- PART TWO --- \\

        List<Seed> seedList = new ArrayList<>();

        String seedLine = input.get(0);
        String[] seedRanges = seedLine.split(":")[1].strip().split(" ");
        for (int i = 0; i < seedRanges.length; i += 2) {
            Long start = Long.parseLong(seedRanges[i].strip());
            Long range = Long.parseLong(seedRanges[i + 1].strip());
            seedList.add(new Seed(start, range));
        }

        Long minLocation = 0L;
        Long reverted = -1L;
        System.out.println("----------- PART TWO -------------");
        while (!inSeeds(seedList, reverted)) {
            minLocation++;
            reverted = revertMap(
                    revertMap(
                            revertMap(
                                    revertMap(
                                            revertMap(
                                                    revertMap(
                                                            revertMap(minLocation, humidityToLocationMaps),
                                                            temperatureToHumidityMaps
                                                    ),
                                                    lightToTemperatureMaps
                                            ),
                                            waterToLightMaps
                                    ),
                                    fertilizerToWaterMaps
                            ),
                            soilToFertilizerMaps
                    ),
                    seedToSoilMaps
            );
        }
        System.out.println(minLocation);
    }

    public static void getMap(List<String> input, int i, String prefix, List<SeedMap> map) {
        String line = input.get(i);
        if (line.startsWith(prefix)) {
            while ((i + 1) < input.size() && !(line = input.get(++i).strip()).isEmpty()) {
                String[] splitted = line.split(" ");
                map.add(new SeedMap(
                        Long.parseLong(splitted[1].strip()),
                        Long.parseLong(splitted[0].strip()),
                        Long.parseLong(splitted[2].strip())
                ));
            }
        }
    }

    public static Long map(Long seed, List<SeedMap> maps) {
        Long result = seed;
        for (SeedMap map : maps) {
            Long mapped = map.convert(seed);
            if (!mapped.equals(seed)) {
                result = mapped;
                break;
            }
        }
        return result;
    }

    public static Long revertMap(Long num, List<SeedMap> maps) {
        Long result = num;
        for (SeedMap map : maps) {
            Long mapped = map.revert(num);
            if (!mapped.equals(num)) {
                result = mapped;
                break;
            }
        }
        return result;
    }

    public static boolean inSeeds(List<Seed> seeds, long num) {
        for (Seed seed : seeds) {
            if (num >= seed.number() && num < (seed.number() + seed.range()))
                return true;
        }
        return false;
    }
}
