package net.averak.cap.domain.client

import net.averak.cap.domain.model.Project

/**
 * 時間のかかる処理を非同期で行うためのクライアント
 */
interface IPubSubClient {

    fun launchProjectContainer(project: Project)

}
