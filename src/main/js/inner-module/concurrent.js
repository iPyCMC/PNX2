// noinspection JSUnresolvedVariable,JSUnresolvedFunction

import {id} from ":plugin-id"

import {CommonJSPlugin as CommonJSPluginClass} from "cn.nukkit.plugin.CommonJSPlugin"
import {JSConcurrentManager as JSConcurrentManagerClass} from "cn.nukkit.plugin.js.JSConcurrentManager"

const jsPlugin = CommonJSPluginClass.jsPluginIdMap.get(id);
const concurrentManager = new JSConcurrentManagerClass(jsPlugin);

/**
 * 将对象包装为原子对象
 * @param obj
 */
export function atomic(obj) {
    return concurrentManager.warpSafe(obj);
}

/**
 * 获取原子对象等待最长时间，单位ms，默认30000
 * @type {number}
 */
export function getAtomicTimeout() {
    return concurrentManager.getLockTimeout();
}

/**
 * 设置原子对象等待最长时间，单位ms，默认30000
 * @param timeout {number}
 */
export function setAtomicTimeout(timeout) {
    return concurrentManager.setLockTimeout(timeout);
}

export class Worker {
    /**
     * 创建一个Worker
     * @param sourcePath {string} worker源代码路径
     * @param startImmediately {boolean} 是否立即启动worker执行，默认true
     */
    constructor(sourcePath, startImmediately = true) {
        this.jsWorker = concurrentManager.createWorker(sourcePath);
        this.jsWorker.init();
        Object.defineProperty(this, 'onmessage', {
            get: function () {
                return this.jsWorker.getSourceReceiveCallback();
            },
            set: function (callback) {
                return this.jsWorker.setSourceReceiveCallback(callback);
            }
        });
        if (startImmediately) {
            this.jsWorker.start();
        }
    }

    /**
     * 启动尚未启动的worker
     */
    start() {
        this.jsWorker.start();
    }

    /**
     * 向Worker发送信息，返回Worker的onmessage函数的返回值
     * @param values
     * @returns {any}
     */
    postMessage(...values) {
        return this.jsWorker.postMessage(values);
    }

    /**
     * 向Worker发送异步信息，返回一个Promise
     * @param values
     * @returns {Promise<any>}
     */
    postMessageAsync(...values) {
        return new Promise(this.jsWorker.postMessageAsync(values));
    }

    /**
     * 终结（强制停止）此Worker
     */
    terminate() {
        this.jsWorker.close();
    }
}

export class Job {
    /**
     * 创建一个Job
     * @param sourcePath {string} Job源代码路径
     * @param startImmediately {boolean} 是否立即启动job，默认true
     */
    constructor(sourcePath, startImmediately = true) {
        this.job = concurrentManager.createJob(sourcePath);
        this.job.init();
        if (startImmediately) {
            this.job.start();
        }
    }

    /**
     * 执行Job的main函数并传入指定参数
     * @param args {...any}
     * @returns {Promise<any>}
     */
    work(...args) {
        return new Promise(this.job.work(args));
    }

    /**
     * 终结（强制停止）此Job
     */
    terminate() {
        this.job.close();
    }
}