// Copyright (C) 2015-2017 Open Imaging, Inc.
//           (C) 2015 Regents of the University of California
//
// LICENSE:      This file is distributed under the BSD license.
//               License text is included with the source distribution.
//
//               This file is distributed in the hope that it will be useful,
//               but WITHOUT ANY WARRANTY; without even the implied warranty
//               of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
//
//               IN NO EVENT SHALL THE COPYRIGHT OWNER OR
//               CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
//               INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES.

package org.micromanager.display.inspector;

import org.micromanager.MMGenericPlugin;
import org.micromanager.display.DataViewer;

/**
 * A plugin providing a panel in the Inspector window.
 *
 * To create an inspector plugin, annotate your class like this:
 * <pre><code>
 * {@literal @}Plugin(type = InspectorPlugin.class,
 *       priority = Prioroty.NORMAL_PRIORITY,  // Suggests order in inspector
 *       name = "My Inspector Plugin",         // User-visible title
 *       description = "Do Wonderful Things")  // Tooltip
 * public class MyInspectorPlugin implements InspectorPlugin {
 *    // ...
 * }
 * </code></pre>
 */
public interface InspectorPanelPlugin extends MMGenericPlugin {
   /**
    * Tell whether the InspectorPanel provided by this plugin is applicable to
    * a given DataViewer.
    *
    * @param viewer the {@code DataViewer} instance
    * @return true if this plugin works with {@code viewer}
    */
   boolean isApplicableToDataViewer(DataViewer viewer);

   /**
    * Return whether the inspector panel implemented by this plugin should be
    * presented in the expanded state by default.
    *
    * This method must return a fixed value. Remembering user preference is
    * handled by the system.
    *
    * @return true if the panel should be expanded by default
    */
   default boolean isPanelExpandedByDefault() {
      return false;
   }

   InspectorPanelController createPanelController();
}