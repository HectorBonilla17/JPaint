# JPaint
Java-Based Maven project that uses various pattern in order to make a MsPaint-like cavnas, with the ability to create varying shapes with color & border. The project also implements a undo & redo functions for the all shapes create in sequence, in addition it has a copy and paste functions for the varying shapes based on the highlighted as the given moment when copy was pressed. 

-------------------------------------------------

Patterns Implemented in Project: Command, CommandFactory, Strategy and Decorator pattern 

-------------------------------------------------

Pattern Implementation:  

Command Pattern: The pattern is implemented using the ICommand interface (see controller.interfaces). For classes that implement this interface & pattern, see CommandController.java and Create/Copy/Delete/Move/Paste/Select-ShapeCommand.java (all in controller.command) 

CommandFactory Pattern: The pattern is implemented using a CommandFactory class that returns a Command object (see controller.interfaces), the Command objects being either Create/Copy/Delete/Move/Paste/Select-ShapeCommand to the CommandController (see all in controller.command). 

Strategy Pattern: The pattern is implemented using the ShapeStrategy interface (see model.interfaces). For classes that implement this interface & pattern, see Ellipse/Rectangle/Triangle/FilledIn/Outine/FilledInAndOutline/SelectionOutline-Strategy.java (see model.shapeInformation for all of them). In Addition, ShapeProperties.java (see model.shapeInformation) gets the needed ShapeStrategy for its shape properties and calls upon the ShapeStrategy object's drawStrategy() method to draw the shape. 
