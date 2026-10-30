# Mosaic

A multi-feature Java generative (not AI!) art studio built on a custom mosaic grid rendering engine. Originally assigned as a project for a computer science class, the program has been expanded much further to support different modes beyond the original assignment.

Files `Mosaics.java` and `MosaicsCreator.java` were given by my teacher, but were since heavily modified to allow the different program modes. All other programs are written by me.

---

## Must know!
* **How to Download** *
   * Go to https://ayaand.itch.io/mosaic
   * Download zipped file, extract
   * Run MosaicsStudio.exe

* **Controls:**
    * Press S to save a screenshot of any window
    * Click and drag anywhere to color cells for the frame
    * Close window to exit

* **Steps to run:**
    * Specify mosaic grid dimensions
    * Choose unit shape
    * Choose execution mode (make sure to try them all!)

---

## Features & Architecture

* **Rendering/Class Subroutine Engines (`MosaicsPanel.java`, `Mosaics.java`):**
  * Supports multiple cell shapes including rectangles, circles, rounded rectangles, and diamonds
  * Includes configurable 3D lighting, cell grouting outlines, and built-in image capture

* **Sketchpad & Exporter (`MosaicExporter.java`):**
  * Mouse input capture for freehand drawing on cells anywhere in the canvas
  * PNG screenshot export (`S` key)

* **Different Program Modes (Chosen by user in `MosaicsCreator.java`):**
  * **Manual Art Engine (`ManualArtEngine.java`):** Original project for my class; takes row-by-row user input and does an infinite animation after all rows are colored
  * **Game of Life Engine (`GameOfLifeEngine.java`):** Cellular spread simulation which follows Conway's rules and includes dynamic colors
  * **Wave & Ripple Generator (`WaveEngine.java`):** Shows beautiful repeated patterns on the screen using trigonometric functions to create colors and loop (my favorite mode!)
  * **Particle & Firework Engine (`ParticleEngine.java`):** Physics simulation that shows the movement of particles within a plane
