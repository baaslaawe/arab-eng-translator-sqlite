/*
 * Copyright (c) 2016 Richard C.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*
 * Copyright (c) 2016 Richard C.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.eng.arab.translator.androidtranslator.instrument;

/**
 * Created by Richard on 04/02/2017.
 */

import org.junit.Test;

import com.eng.arab.translator.androidtranslator.instrument.ElectricBassGuitar;
import com.eng.arab.translator.androidtranslator.instrument.ElectricGuitar;

public class InstrumentUnitTest {
    @Test
    public void test() throws Exception {
        ElectricGuitar guitar = new ElectricGuitar();
        ElectricBassGuitar bassGuitar = new ElectricBassGuitar();

        guitar.play();
        bassGuitar.play();

        guitar = new ElectricGuitar(7);
        bassGuitar = new ElectricBassGuitar(5);

        guitar.play();
        bassGuitar.play();
    }
}