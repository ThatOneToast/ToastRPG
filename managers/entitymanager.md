---
description: EntityManager, what does it do?
---

# EntityManager

The entity manager tracks all entities and holds a specific set of events to that entity. When you create your own custom entities, using the `Monster` & `MonsterType` classes from `ToastRPG` you will be required to pass a `EntityHandler`.

The EntityHandler is what dictates to happen when the monster `Spawns`, `Dies`, or `Targets`.  &#x20;

This will lead nicely into `Monsters`
