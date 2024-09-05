// noinspection JSUnresolvedVariable

import {id} from ":plugin-id"
import {Nukkit as NukkitClass} from "cn.nukkit.Nukkit"
import {CommonJSPlugin as CommonJSPluginClass} from "cn.nukkit.plugin.CommonJSPlugin"
import {JSEventManager} from "cn.nukkit.plugin.js.JSEventManager"
import {EventPriority as EventPriorityClass} from "cn.nukkit.event.EventPriority"

const jsPlugin = CommonJSPluginClass.jsPluginIdMap.get(id);
const eventManager = new JSEventManager(jsPlugin);

/**
 * 事件优先级枚举，越低越优先执行
 */
export class EventPriority {
    static LOWEST = EventPriorityClass.LOWEST;
    static LOW = EventPriorityClass.LOW;
    static NORMAL = EventPriorityClass.NORMAL;
    static HIGH = EventPriorityClass.HIGH;
    static HIGHEST = EventPriorityClass.HIGHEST;
    static MONITOR = EventPriorityClass.MONITOR;
}

/**
 * PowerNukkitX服务端操作对象
 */
export const PowerNukkitX = {
    /**
     * 获取PowerNukkitX服务器版本
     * @return {string}
     */
    getServerVersion: () => {
        return NukkitClass.GIT_COMMIT;
    },
    /**
     *
     * @param fullEventName {string}
     * @param priority {EventPriority}
     * @param callback {(event) => void}
     * @returns {boolean}
     */
    listenEvent: (fullEventName, priority, callback) => {
        return eventManager.register(fullEventName, priority, callback);
    },
    /**
     * @returns {{
     *     getCommandName: () => string,
     *     setCommandName: (string) => this,
     *     getDescription: () => string,
     *     setDescription: (string) => this,
     *     getUsageMessage: () => string,
     *     setUsageMessage: (string) => this,
     *     getAlias: () => string[],
     *     setAlias: (...string) => this,
     *     addAlias: (...string) => this,
     *     getPermission: () => string,
     *     setPermission: (string) => this,
     *     getPermissionMessage: () => string,
     *     setPermissionMessage: (string) => this,
     *     getCommandParameters: () => Map<string, Object[]>,
     *     setCommandParameters: (parameterJavaMap: Map<string, Object[]>) => this,
     *     getCallback: () => (sender: Object, result: Object, log: Object) => void,
     *     setCallback: (callback: (sender: Object, result: Object, log: Object) => void) => this,
     *     createCommandPattern: (string) => this,
     *     createDefaultPattern: () => this,
     *     addTypeParameter: (name: string, optional: boolean, commandParamType: Object) => this,
     *     addCustomTypeParameter: (name: string, optional: boolean, commandParamType: Object,paramNode : Object,options: Object) => this,
     *     addIntParameter: (name: string, optional: boolean) => this,
     *     addFloatParameter: (name: string, optional: boolean) => this,
     *     addValueParameter: (name: string, optional: boolean) => this,
     *     addWildcardIntParameter: (name: string, optional: boolean) => this,
     *     addTargetParameter: (name: string, optional: boolean) => this,
     *     addWildcardTargetParameter: (name: string, optional: boolean) => this,
     *     addStringParameter: (name: string, optional: boolean) => this,
     *     addBlockPositionParameter: (name: string, optional: boolean) => this,
     *     addPositionParameter: (name: string, optional: boolean) => this,
     *     addMessageParameter: (name: string, optional: boolean) => this,
     *     addTextParameter: (name: string, optional: boolean) => this,
     *     addJsonParameter: (name: string, optional: boolean) => this,
     *     addSubCommandParameter: (name: string, optional: boolean) => this,
     *     addFilePathParameter: (name: string, optional: boolean) => this,
     *     addOperatorParameter: (name: string, optional: boolean) => this,
     *     addEnumParameter: (name: string, optional: boolean, ...string) => this,
     *     addCustomEnumParameter: (name: string, optional: boolean, data: Object, paramNode: Object, options: Object[]) => this,
     *     addEnumBlockParameter: (name: string, optional: boolean) => this,
     *     addEnumEntityParameter: (name: string, optional: boolean) => this,
     *     addEnumItemParameter: (name: string, optional: boolean) => this,
     *     addEnumBooleanParameter: (name: string, optional: boolean) => this,
     *     addEnumGameModeParameter: (name: string, optional: boolean) => this,
     *     register: () => boolean,
     *     registerOld: () => boolean,
     *     getBuildCommand: () => Object
     * }}
     */
    commandBuilder: () => eventManager.commandBuilder()
}