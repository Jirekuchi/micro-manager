///////////////////////////////////////////////////////////////////////////////
//PROJECT:       Micro-Manager
//SUBSYSTEM:     Data API implementation
//-----------------------------------------------------------------------------
//
// AUTHOR:       Chris Weisiger, 2015
//
// COPYRIGHT:    University of California, San Francisco, 2015
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

package org.micromanager.data.internal;

import java.io.IOException;
import org.micromanager.PropertyMap;
import org.micromanager.data.Annotation;
import org.micromanager.data.Coords;
import org.micromanager.data.Datastore;

/**
 * A simple helper class to handle access of comments.
 */
public final class CommentsHelper {
   /** File that comments are saved in. */
   private static final String COMMENTS_FILE = "comments.txt";
   /** String key used to access comments in annotations. */
   private static final String COMMENTS_KEY = "comments";

   /**
    * Returns the summary comment for the specified Datastore, or "" if it
    * does not exist.
    */
   public static String getSummaryComment(Datastore store) throws IOException {
      if (!store.hasAnnotation(COMMENTS_FILE)) {
         return "";
      }
      return store.getAnnotation(COMMENTS_FILE).getGeneralAnnotation().getString(COMMENTS_KEY, "");
   }

   /**
    * Write a new summary comment for the given Datastore.
    */
   public static void setSummaryComment(Datastore store, String comment) throws IOException {
      Annotation annotation = store.getAnnotation(COMMENTS_FILE);
      PropertyMap prop = annotation.getGeneralAnnotation();
      if (prop == null) {
         prop = new DefaultPropertyMap.Builder().build();
      }
      prop = prop.copy().putString(COMMENTS_KEY, comment).build();
      annotation.setGeneralAnnotation(prop);
   }

   /**
    * Returns the comment for the specified Image in the specified Datastore,
    * or "" if it does not exist.
    */
   public static String getImageComment(Datastore store, Coords coords) throws IOException {
      if (!store.hasAnnotation(COMMENTS_FILE)) {
         return "";
      }
      Annotation annotation = store.getAnnotation(COMMENTS_FILE);
      if (annotation.getImageAnnotation(coords) == null) {
         return "";
      }
      return annotation.getImageAnnotation(coords).getString(COMMENTS_KEY,
            "");
   }

   /**
    * Write a new image comment for the given Datastore.
    */
   public static void setImageComment(Datastore store, Coords coords,
         String comment) throws IOException {
      Annotation annotation = store.getAnnotation(COMMENTS_FILE);
      PropertyMap prop = annotation.getImageAnnotation(coords);
      if (prop == null) {
         prop = new DefaultPropertyMap.Builder().build();
      }
      prop = prop.copy().putString(COMMENTS_KEY, comment).build();
      annotation.setImageAnnotation(coords, prop);
   }

   public static void saveComments(Datastore store) throws IOException {
      Annotation annotation = store.getAnnotation(COMMENTS_FILE);
      annotation.save();
   }

   /**
    * Return true if there's a comments annotation.
    */
   public static boolean hasAnnotation(Datastore store) throws IOException {
      return store.hasAnnotation(COMMENTS_FILE);
   }

   /**
    * Create a new comments annotation.
    */
   public static void createAnnotation(Datastore store) throws IOException {
      //store.createNewAnnotation(COMMENTS_FILE);
      throw new UnsupportedOperationException("TODO");
   }
}
