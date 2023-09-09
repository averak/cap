package net.averak.cap.domain.model

import net.averak.cap.domain.primitive.common.ID
import net.averak.cap.domain.primitive.cron_job.CronJobCommand
import net.averak.cap.domain.primitive.cron_job.CronJobExpression
import net.averak.cap.domain.primitive.project.DockerImage

/**
 * プロジェクトのバッチ処理として実行される Cron Job
 */
class CronJob(
    val id: ID,
    val expression: CronJobExpression,
    // イメージの ENTRYPOINT でコマンド指定される場合もあるので、NULL を許容する
    val command: CronJobCommand?,
    val dockerImage: DockerImage,
) {
}
