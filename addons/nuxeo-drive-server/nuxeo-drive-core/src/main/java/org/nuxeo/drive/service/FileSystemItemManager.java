/*
 * (C) Copyright 2012 Nuxeo SA (http://nuxeo.com/) and contributors.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser General Public License
 * (LGPL) version 2.1 which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl.html
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * Contributors:
 *     Antoine Taillefer <ataillefer@nuxeo.com>
 */
package org.nuxeo.drive.service;

import java.security.Principal;
import java.util.List;

import org.nuxeo.drive.adapter.FileItem;
import org.nuxeo.drive.adapter.FileSystemItem;
import org.nuxeo.drive.adapter.FolderItem;
import org.nuxeo.drive.service.impl.FileSystemItemManagerImpl;
import org.nuxeo.ecm.core.api.Blob;
import org.nuxeo.ecm.core.api.CoreInstance;
import org.nuxeo.ecm.core.api.CoreSession;

/**
 * Provides an API to manage usual file system operations on a {@link FileSystemItem} given its id. Allows the following
 * actions:
 * <ul>
 * <li>Read</li>
 * <li>Read children</li>
 * <li>Create</li>
 * <li>Update</li>
 * <li>Delete</li>
 * <li>Rename</li>
 * <li>Move</li>
 * </ul>
 *
 * @author Antoine Taillefer
 * @see FileSystemItemManagerImpl
 */
public interface FileSystemItemManager {

    /**
     * Gets a session bound to the given repository for the given principal.
     *
     * @deprecated since 7.2, use {@link CoreInstance#openCoreSession(String, Principal)} instead.
     */
    @Deprecated
    CoreSession getSession(String repositoryName, Principal principal);

    /*------------- Read operations ----------------*/
    /**
     * Gets the children of the top level {@link FolderItem} for the given principal.
     *
     * @deprecated use getTopLevelFolder#getChildren instead
     */
    @Deprecated
    List<FileSystemItem> getTopLevelChildren(Principal principal);

    /**
     * Gets the top level {@link FolderItem} for the given principal.
     */
    FolderItem getTopLevelFolder(Principal principal);

    /**
     * Returns true if a {@link FileSystemItem} with the given id exists for the given principal.
     *
     * @see FileSystemItemFactory#exists(String, Principal)
     */
    boolean exists(String id, Principal principal);

    /**
     * Gets the {@link FileSystemItem} with the given id for the given principal.
     *
     * @return the {@link FileSystemItem} or null if none matches the given id
     * @see FileSystemItemFactory#getFileSystemItemById(String, Principal)
     */
    FileSystemItem getFileSystemItemById(String id, Principal principal);

    /**
     * Gets the {@link FileSystemItem} with the given id and parent id for the given principal.
     *
     * @return the {@link FileSystemItem} or null if none matches the given id and parent id
     * @see #getFileSystemItemById(String, Principal)
     * @since 6.0
     */
    FileSystemItem getFileSystemItemById(String id, String parentId, Principal principal);

    /**
     * Gets the children of the {@link FileSystemItem} with the given id for the given principal.
     *
     * @see FolderItem#getChildren()
     */
    List<FileSystemItem> getChildren(String id, Principal principal);

    /**
     * Return true if the {@link FileSystemItem} with the given source id can be moved to the {@link FileSystemItem}
     * with the given destination id for the given principal.
     *
     * @see FileSystemItem#getCanMove(String)
     */
    boolean canMove(String srcId, String destId, Principal principal);

    /*------------- Write operations ----------------*/
    /**
     * Creates a folder with the given name in the {@link FileSystemItem} with the given id for the given principal.
     *
     * @see FolderItem#createFolder(String)
     */
    FolderItem createFolder(String parentId, String name, Principal principal);

    /**
     * Creates a file with the given blob in the {@link FileSystemItem} with the given id for the given principal.
     *
     * @see FolderItem#createFile(Blob)
     */
    FileItem createFile(String parentId, Blob blob, Principal principal);

    /**
     * Updates the {@link FileSystemItem} with the given id with the given blob for the given principal.
     *
     * @see FileItem#setBlob(Blob)
     */
    FileItem updateFile(String id, Blob blob, Principal principal);

    /**
     * Updates the {@link FileSystemItem} with the given id and parent id with the given blob for the given principal.
     *
     * @see #updateFile(String, Blob, Principal)
     * @since 6.0
     */
    FileItem updateFile(String id, String parentId, Blob blob, Principal principal);

    /**
     * Deletes the {@link FileSystemItem} with the given id for the given principal.
     *
     * @see FileSystemItem#delete()
     */
    void delete(String id, Principal principal);

    /**
     * Deletes the {@link FileSystemItem} with the given id and parent id for the given principal.
     *
     * @see #delete(String, Principal)
     * @since 6.0
     */
    void delete(String id, String parentId, Principal principal);

    /**
     * Renames the {@link FileSystemItem} with the given id with the given name for the given principal.
     *
     * @see FileSystemItem#rename(String)
     */
    FileSystemItem rename(String id, String name, Principal principal);

    /**
     * Moves the {@link FileSystemItem} with the given source id to the {@link FileSystemItem} with the given
     * destination id for the given principal.
     *
     * @see FileSystemItem#move(String)
     */
    FileSystemItem move(String srcId, String destId, Principal principal);

}