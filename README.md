# Ear Clipping Triangulation

This program is designed to demonstate how the ear-clipping algorithm for triangulating a polygon works. It has 4 phases, as explained below.

The first phase is the **Drawing** phase where users draw a simple (no holes) polygon to be triangulated.

The second phase is the **Initialization** phase where the algorithm identifies all the ears (points whose two neighboring vertices in the polygon form a diagonal) present at the beginning of the algorithm

The third phase is the **Clipping** phase where the algorithm goes point by point, drawing the diagonal of those labelled ear and updating the vertices incident on those diagonals in terms of whether they have now become ears. Once completed there will be a triangulation

The fourth phase is the **Triangulation** phase where the program simply shows the triangulation that has been created.