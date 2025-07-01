import java.io.File;
import java.util.HashSet;
import java.util.*;
import java.io.*;
import java.util.ArrayList;
public class SimilarArtists {
    public SimilarArtists(){
        Runner runner = new Runner();
    }
    class Artist{
        private String name;
        private int uniqueID;
        public Artist(String name){
            this.name = name;
            uniqueID = name.hashCode();
        }
        public String getName(){
            return name;
        }
        public int hashCode(){
            return uniqueID;
        }
        public String toString(){
            return name;
        }
        public boolean equals(Object obj){
            if(obj == null || getClass() != obj.getClass()){
                return false;
            }
            return this.hashCode()==((Artist) obj).hashCode();
        }
    }

    class Edge{
        private Artist artist;
        private Artist similar;
        private int uniqueID;
        public Edge(Artist artist, Artist similar){
            this.artist = artist;
            this.similar = similar;
            uniqueID = artist.hashCode() + similar.hashCode();
        }
        public Artist getArtist(){
            return artist;
        }
        public Artist getSimilar(){
            return similar;
        }
        public int hashCode(){
            return uniqueID;
        }
        public String toString(){
            return artist.getName() + " is similar to " + similar.getName();
        }
        public boolean equals(Object obj){
            if(obj == null || getClass() != obj.getClass()){
                return false;
            }
            return this.hashCode()==((Edge) obj).hashCode();
        }
    }

    class Graph{
        private HashSet<Artist> artists;
        private HashSet<Edge> edges;
        public Graph(){
            artists = new HashSet<>();
            edges = new HashSet<>();
        }
        public HashSet<Artist> getArtists(){
            return artists;
        }
        public HashSet<Edge> getEdges(){
            return edges;
        }
        public void addArtist(Artist a){
            artists.add(a);
        }
        public void addEdge(Artist source, Artist destination){
            edges.add(new Edge(source, destination));
        }
    }

    class Runner{
        HashMap<Artist, HashSet<Edge>> artistMap;
        Artist start, end;
        Graph graph;
        Stack<Artist> currentPath;
        HashSet<Artist> visited;
        public Runner(){
            artistMap = new HashMap<>();
            graph = new Graph();
            File file = new File("/Users/arnavgoel/IdeaProjects/SimilarArtists/src/SimilarArtists.txt");
            try{
                BufferedReader input = new BufferedReader(new FileReader(file));
                String text;
                while ((text = input.readLine()) != null) {
                    String[] info = text.split(", ");
                    Artist a1 = new Artist(info[0]);
                    Artist a2 = new Artist(info[1]);
                    //System.out.println(a1 + " " + a2);
                    graph.addArtist(a1);
                    graph.addArtist(a2);
                    graph.addEdge(a1, a2);
                    graph.addEdge(a2, a1);
                    if(!artistMap.containsKey(a1)){
                        artistMap.put(a1, new HashSet<Edge>());
                    }
                    if(!artistMap.containsKey(a2)){
                        artistMap.put(a2, new HashSet<Edge>());
                    }
                    artistMap.get(a1).add(new Edge(a1, a2));
                    artistMap.get(a2).add(new Edge(a2, a1));
                    //System.out.println("Edges – Connecting artists with similar");
                    for(Edge edge: graph.getEdges()){
                        //System.out.println("\t"+edge);
                    }
                    for(Artist startingArtist: graph.getArtists()){
                        //System.out.println(startingArtist);
                        for(Artist endArtist: graph.getArtists()){
                            if(startingArtist != endArtist){
                                currentPath = new Stack<>();
                                visited = new HashSet<>();
                                start = startingArtist;
                                end = endArtist;
                                //System.out.println(start + " " + end);
                                dft(start, end);
                            }
                        }
                    }
                }
            }catch(IOException e){

            }
        }
        public void dft(Artist currentArtist, Artist destination){
            currentPath.push(currentArtist);
            visited.add(currentArtist);
            if(currentArtist.equals(destination)){
                printCurrentPath();
            }
            else{
                for(Edge edge: graph.getEdges()){
                    Artist artist = edge.getArtist();
                    Artist similar = edge.getSimilar();
                    if(visited.contains(artist) && !visited.contains(similar)){
                        dft(similar, destination);
                    }
                    if(visited.contains(similar) && !visited.contains(artist)){
                        dft(artist, destination);
                    }
                }
            }
        }

        public void printCurrentPath(){
            String output = "";
            while(!currentPath.isEmpty()){
                output = currentPath.pop().toString() + output;
                if(!currentPath.isEmpty()){
                    output = "->" + output;
                }
                System.out.print("\t" + output);
                //System.out.println(currentPath.size());
            }
        }
    }

    public static void main(String[] args) {
        SimilarArtists app = new SimilarArtists();
    }
}