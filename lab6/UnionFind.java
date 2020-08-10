public class UnionFind {

    public int[] verts;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        verts = new int[n];
        for(int i = 0; i < verts.length; i+=1){
        	verts[i] = -1;
        }
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        if(vertex==null || vertex < 0 || vertex >= verts.length){
        	throw new IndexOutOfBoundsException("Entered index is not valid");
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
    	validate(v1);
        int parent = parent(v1);
        if(parent < 0) {
        	return -1*parent;
        } else {
        	return sizeOf(parent);
        }
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
    	validate(v1);
    	if(v1 < 0){
    		return v1;
    	} else {
    		return verts[v1];
    	}
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
    	validate(v1);
    	validate(v2);
    	return (find(v1) == find(v2));
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
    	validate(v1);
    	validate(v2);
        int sv1 = sizeOf(v1);
        int r1 = find(v1);
        int sv2 = sizeOf(v2);
        int r2 = find(v2);
        if(sv1 > sv2){
        	verts[r1] += verts[r2];
        	verts[r2] = r1;
        } else {
        	verts[r2] += verts[r1];
        	verts[r1] = r2;
        }

    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
    	validate(vertex);
    	int p = vertex;
    	while(parent(p) >= 0) {
    		p = parent(p);
    	}
    	return p;
    }

}
