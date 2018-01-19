package app;

public class Edge {

    public float cij;
    public int v1;
    public int v2;
    public int trangle = 0;
    public Edge(int v1, int v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public Edge(float cij, int v1, int v2,int trangle) {
        this.trangle = trangle;
        this.cij = cij;
        this.v1 = v1;

        this.v2 = v2;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "cij=" + cij +
                ", v1=" + v1 +
                ", v2=" + v2 +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edge edge = (Edge) o;

        if (v1 != edge.v1) return false;
        return v2 == edge.v2;
    }

}
