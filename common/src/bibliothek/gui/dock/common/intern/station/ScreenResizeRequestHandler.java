/*
 * Bibliothek - DockingFrames
 * Library built on Java/Swing, allows the user to "drag and drop"
 * panels containing any Swing-Component the developer likes to add.
 * 
 * Copyright (C) 2007 Benjamin Sigg
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 * 
 * Benjamin Sigg
 * benjamin_sigg@gmx.ch
 * CH - Switzerland
 */
package bibliothek.gui.dock.common.intern.station;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Rectangle;

import bibliothek.gui.dock.ScreenDockStation;
import bibliothek.gui.dock.common.intern.CDockable;
import bibliothek.gui.dock.station.screen.ScreenDockDialog;

/**
 * A handler which can change the size of children of a {@link ScreenDockStation}
 * such that the {@link CDockable#getAndClearResizeRequest() preferred size}
 * of the children is met. 
 * @author Benjamin Sigg
 *
 */
public class ScreenResizeRequestHandler extends AbstractResizeRequestHandler {
    /** the station whose children might get resized */
    private ScreenDockStation station;
    
    /**
     * Creates a new handler.
     * @param station the station whose children might get resized
     */
    public ScreenResizeRequestHandler( ScreenDockStation station ){
        this.station = station;
    }
    
    public void handleResizeRequest() {
        for( int i = 0, n = station.getDockableCount(); i<n; i++ ){
            ScreenDockDialog dialog = station.getDialog( i );
            Dimension size = getAndClearResizeRequest( station.getDockable( i ) );
            if( size != null ){
                Insets insets = dialog.getDockableInsets();
                Rectangle bounds = dialog.getBounds();
                
                size = new Dimension( size );
                size.width += insets.left + insets.right;
                size.height += insets.top + insets.bottom;
                
                
                dialog.setRestrictedBounds(
                        bounds.x + (bounds.width - size.width)/2, 
                        bounds.y + (bounds.height - size.height)/2,
                        size.width,
                        size.height );
            }
        }
    }

}
