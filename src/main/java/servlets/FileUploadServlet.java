// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.


package com.google.sps.servlets;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/name")
public class FileUploadServlet extends HttpServlet {

      @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    
    String filename = request.getParameter("filename");
    String code =  request.getParameter("editor");
    String toGet = request.getParameter("fileToGet");

    System.out.println(filename);
    System.out.println(code);
    System.out.println(code);

    Entity taskEntity = new Entity("Task");
    taskEntity.setProperty("filename", filename);
    taskEntity.setProperty("code", code);

    datastore.put(taskEntity);

    response.sendRedirect("/editor.html");
  }

//   @Override
//   public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
//     Query query = new Query("Task").addSort("filename", SortDirection.DESCENDING);

//     DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
//     PreparedQuery results = datastore.prepare(query);

//     String toGet = request.getParameter("fileToGet");

//     String savedCode = "file not found :(";
//     for (Entity entity : results.asIterable()) {
//       String file = (String) entity.getProperty("filename");
//       if (file.equals(toGet)) {
//           savedCode = (String) entity.getProperty("code");
//           break;
//       }
//     }

//     System.out.println("toget: " + toGet);
//     System.out.println(savedCode);

//     Gson gson = new Gson();

//     response.setContentType("application/json;");
//     response.getWriter().println(gson.toJson(savedCode));
//   }
}
