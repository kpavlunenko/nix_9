package ua.com.alevel.util;

import ua.com.alevel.entity.City;
import ua.com.alevel.entity.CityPaths;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class GraphUtil {

    private final String PATH_INPUT = "files/input.txt";
    private final String PATH_OUTPUT = "files/output.txt";
    private int maxCities = 1000;
    private final int INFINITY = 10000000;
    private City citiesList[];
    private int relationMatrix[][];
    private int countOfVertices;
    private int countOfVertexInTree;
    private List<CityPaths> shortestPaths;
    private int currentVertex;
    private int startToCurrent;

    public void parseGraphFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(PATH_INPUT));
             BufferedWriter writer = new BufferedWriter(new FileWriter(PATH_OUTPUT))) {
            while (reader.ready()) {
                maxCities = Integer.parseInt(reader.readLine());
                fillMatrix();
                for (int i = 0; i < maxCities; i++) {
                    String nameOfCity = reader.readLine();
                    addCity(nameOfCity, i);
                    int countOfNeighbours = Integer.parseInt(reader.readLine());
                    for (int j = 0; j < countOfNeighbours; j++) {
                        String pathToNeighbour = reader.readLine();
                        String[] substrings = pathToNeighbour.split(" ");
                        if (substrings.length != 2) {
                            throw new RuntimeException("data in input file is incorrect! Row: " + pathToNeighbour);
                        }
                        addEdge(i, Integer.parseInt(substrings[0]) - 1, Integer.parseInt(substrings[1]));
                    }
                }
                int countOfWays = Integer.parseInt(reader.readLine());
                for (int j = 0; j < countOfWays; j++) {
                    String citiesToFindWay = reader.readLine();
                    String[] substrings = citiesToFindWay.split(" ");
                    if (substrings.length != 2) {
                        throw new RuntimeException("data in input file is incorrect! Row: " + citiesToFindWay);
                    }
                    City currentCity = Arrays.stream(citiesList)
                            .filter(city -> city.getName().equals(substrings[0]))
                            .findFirst()
                            .orElseThrow(() -> new RuntimeException("data in input file is incorrect!"));

                    City finalCity = Arrays.stream(citiesList)
                            .filter(city -> city.getName().equals(substrings[1]))
                            .findFirst()
                            .orElseThrow(() -> new RuntimeException("data in input file is incorrect!"));
                    shortestPath(currentCity.getIndex(), finalCity.getIndex(), writer);
                    clean();
                }
                writer.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException("data in input file is incorrect!");
        }
    }

    private void fillMatrix() {
        citiesList = new City[maxCities];
        relationMatrix = new int[maxCities][maxCities];
        countOfVertices = 0;
        countOfVertexInTree = 0;
        for (int i = 0; i < maxCities; i++) {
            for (int k = 0; k < maxCities; k++) {
                relationMatrix[i][k] = INFINITY;
                shortestPaths = new ArrayList<>();
            }
        }
    }

    private void addCity(String name, int index) {
        citiesList[countOfVertices++] = new City(name, index);
    }

    private void addEdge(int start, int end, int weight) {
        relationMatrix[start][end] = weight;
    }

    private void shortestPath(int startTree, int finalIndex, BufferedWriter writer) {

        citiesList[startTree].setInTree(true);
        countOfVertexInTree = 1;

        for (int i = 0; i < countOfVertices; i++) {
            int tempDist = relationMatrix[startTree][i];
            CityPaths cityPath = new CityPaths(tempDist);
            cityPath.getParentVertices().add(startTree);
            shortestPaths.add(cityPath);
        }
        while (countOfVertexInTree < countOfVertices) {
            int indexMin = getMin();
            int minDist = shortestPaths.get(indexMin).getDistance();
            if (minDist == INFINITY) {
                break;
            } else {
                currentVertex = indexMin;
                startToCurrent = shortestPaths.get(indexMin).getDistance();
            }

            citiesList[currentVertex].setInTree(true);
            countOfVertexInTree++;
            updateShortestPaths();
        }
        displayPaths(startTree, finalIndex);
        writePaths(startTree, finalIndex, writer);
    }

    private int getMin() {
        int minDist = INFINITY;
        int indexMin = 0;
        for (int i = 1; i < countOfVertices; i++) {
            if (!citiesList[i].isInTree() && shortestPaths.get(i).getDistance() < minDist) {
                minDist = shortestPaths.get(i).getDistance();
                indexMin = i;
            }
        }
        return indexMin;
    }

    private void updateShortestPaths() {
        int vertexIndex = 1;
        while (vertexIndex < countOfVertices) {
            if (citiesList[vertexIndex].isInTree()) {
                vertexIndex++;
                continue;
            }
            int currentToFringe = relationMatrix[currentVertex][vertexIndex];
            int startToFringe = startToCurrent + currentToFringe;
            int shortPathDistance = shortestPaths.get(vertexIndex).getDistance();
            if (startToFringe < shortPathDistance) {
                List<Integer> newParents = new ArrayList<>(shortestPaths.get(currentVertex).getParentVertices());
                newParents.add(currentVertex);
                shortestPaths.get(vertexIndex).setParentVertices(newParents);
                shortestPaths.get(vertexIndex).setDistance(startToFringe);
            }
            vertexIndex++;
        }
    }

    private void writePaths(int startTree, int finalIndex, BufferedWriter writer) {
        String shortestWay = "";
        shortestWay = citiesList[startTree].getName() + " -> " + citiesList[finalIndex].getName() + " = ";
        if (shortestPaths.get(finalIndex).getDistance() == INFINITY) {
            shortestWay = shortestWay + "0";
        } else {
            String result = shortestPaths.get(finalIndex).getDistance() + " (";
            List<Integer> parents = shortestPaths.get(finalIndex).getParentVertices();
            for (int j = 0; j < parents.size(); j++) {
                result += citiesList[parents.get(j)].getName() + " -> ";
            }
            shortestWay = shortestWay + result + citiesList[finalIndex].getName() + ")";
        }
        try {
            writer.write(shortestWay);
            writer.write(System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayPaths(int startTree, int finalIndex) {
        System.out.print(citiesList[startTree].getName() + " -> " + citiesList[finalIndex].getName() + " = ");
        if (shortestPaths.get(finalIndex).getDistance() == INFINITY) {
            System.out.println("0");
        } else {
            String result = shortestPaths.get(finalIndex).getDistance() + " (";
            List<Integer> parents = shortestPaths.get(finalIndex).getParentVertices();
            for (int j = 0; j < parents.size(); j++) {
                result += citiesList[parents.get(j)].getName() + " -> ";
            }
            System.out.println(result + citiesList[finalIndex].getName() + ")");
        }
    }

    private void clean() {
        countOfVertexInTree = 0;
        for (int i = 0; i < countOfVertices; i++) {
            citiesList[i].setInTree(false);
        }
        countOfVertexInTree = 0;
        for (int i = 0; i < maxCities; i++) {
            for (int k = 0; k < maxCities; k++) {
                shortestPaths = new ArrayList<>();
            }
        }
    }
}
